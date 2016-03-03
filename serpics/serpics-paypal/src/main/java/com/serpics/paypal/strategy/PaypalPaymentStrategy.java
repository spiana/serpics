package com.serpics.paypal.strategy;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Link;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.core.rest.OAuthTokenCredential;
import com.paypal.core.rest.PayPalRESTException;
import com.serpics.base.data.model.Currency;
import com.serpics.commerce.PaymentException;
import com.serpics.commerce.PaymentIntent;
import com.serpics.commerce.PaymentState;
import com.serpics.commerce.PaymentTransactionState;
import com.serpics.commerce.PaymentTransactionType;
import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.commerce.data.model.AbstractOrderitem;
import com.serpics.commerce.data.model.Payment;
import com.serpics.commerce.data.model.PaymentTransaction;
import com.serpics.commerce.data.model.Paymethod;
import com.serpics.commerce.data.model.Paymethodlookup;
import com.serpics.commerce.data.repositories.PaymentRepository;
import com.serpics.commerce.data.repositories.PaymentTransactionRepository;
import com.serpics.commerce.services.PaymentService;
import com.serpics.commerce.strategies.PaymentStrategy;
import com.serpics.stereotype.StoreStrategy;

@StoreStrategy("paypalPaymentStrategy")
public class PaypalPaymentStrategy implements PaymentStrategy {

	@Resource
	PaymentService paymentService ;
	
	@Resource
	PaymentTransactionRepository paymentTransactionRepository;
	
	@Resource
	PaymentRepository paymentRepository;
	
	
	@Override
	public Payment createPayment(AbstractOrder order, PaymentIntent intent)
			throws PaymentException {
		
		Paymethod payMethod = order.getPaymethod();
		
		Paymethodlookup p = paymentService.findPaymethodInfo(payMethod);
		
			if (intent == null)
				intent = p.getIntent();
			
			Amount amount = new Amount();
			amount.setCurrency(order.getCurrency().getIsoCode());
			
			DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
			formatSymbols.setDecimalSeparator('.');
			DecimalFormat decimalFormat = new DecimalFormat("#.00",formatSymbols);
			NumberFormat numberFormat =  NumberFormat.getInstance();
			
			String orderAmount = decimalFormat.format(order.getOrderAmount());

			amount.setTotal(orderAmount);
			
			Transaction t = new Transaction();
			t.setAmount(amount);
			List<Item> items = new ArrayList<Item>();
			
			for (AbstractOrderitem item : order.getItems()){
				Item paypalItem = new Item();
				//Required
				paypalItem.setQuantity(numberFormat.format(item.getQuantity()));
				paypalItem.setName(item.getSkuDescription());
				paypalItem.setPrice(decimalFormat.format(item.getSkuCost()));
				paypalItem.setSku(item.getSku());

				paypalItem.setCurrency(order.getCurrency().getIsoCode());
				items.add(paypalItem);
			}
			t.setDescription("Order Id: "+order.getId().toString());
			ItemList itemList = new ItemList();
			itemList.setItems(items);
			t.setItemList(itemList);
			
		
			
			RedirectUrls urls= new RedirectUrls();
			urls.setCancelUrl(p.getCancelURL());
			urls.setReturnUrl(p.getReturnURL());
			
			Payer payer = new Payer();
			payer.setPaymentMethod("paypal");
			
			
			com.paypal.api.payments.Payment paymentRequest = new com.paypal.api.payments.Payment();
			paymentRequest.setIntent(intent.toString().toLowerCase());
			paymentRequest.setPayer(payer);
			paymentRequest.setRedirectUrls(urls);
			
		
			java.util.List<Transaction> _l = new ArrayList<Transaction>();
			_l.add(t);
			paymentRequest.setTransactions(_l);
			
			com.paypal.api.payments.Payment paypalPayment = null;
			try {
				 paypalPayment =  makePaypalRequest(paymentRequest, p);
				
			} catch (PayPalRESTException e) {
				throw new PaymentException(e);
			}
			
			Payment payment = new Payment();
			payment.setIntent(intent);
			payment.setAmount(order.getOrderAmount());
			payment.setOrder(order);
			payment.setPaymethod(order.getPaymethod());
			payment.setCurrency(order.getCurrency());
			payment.setAuthorizedAmount(0D);
			payment.setCaptureAmount(0D);
			payment.setRefoundAmount(0D);

			payment.setState(PaymentState.valueOf(paypalPayment.getState().toUpperCase()));
			payment.setPaymentIdentifier(paypalPayment.getId());

			payment.setAuthorizedURL(getApprovalLink(paypalPayment.getLinks()));
			

			paymentRepository.saveAndFlush(payment);
		
		
		return payment;
	}

	private String getApprovalLink(List<Link> links ){
		for (Link link : links) {
			if (link.getRel().equals("approval_url"))
			 return link.getHref();
			
		}
		return null;
	}
	private com.paypal.api.payments.Payment makePaypalRequest(com.paypal.api.payments.Payment payment , Paymethodlookup payInfo) throws PayPalRESTException{
		String token = new OAuthTokenCredential(payInfo.getMerchantKey(),
				payInfo.getMerchantSecret()).getAccessToken();
		
		com.paypal.api.payments.Payment c = payment.create(token);
		return c;
	}
	
	private com.paypal.api.payments.Payment makePaypalConfirm(Paymethodlookup payInfo , Payment payment) throws PayPalRESTException{
		OAuthTokenCredential tokenCredential =
				  new OAuthTokenCredential(payInfo.getMerchantKey(), payInfo.getMerchantSecret());
				  
				String accessToken = tokenCredential.getAccessToken();

				com.paypal.api.payments.Payment paypalPayment = com.paypal.api.payments.Payment.get(accessToken,payment.getPaymentIdentifier());

				PaymentExecution paymentExecution = new PaymentExecution();
				paymentExecution.setPayerId(payment.getPayerId());

				com.paypal.api.payments.Payment newPayment = paypalPayment.execute(accessToken, paymentExecution);
				
				return newPayment;
	}
	
	@Override
	public Payment authorizePayment(Payment payment) throws PaymentException {
		Paymethod payMethod = payment.getPaymethod();
		Paymethodlookup p = paymentService.findPaymethodInfo(payMethod);
	
		com.paypal.api.payments.Payment paypalPayment = null;
		try {
		 paypalPayment = makePaypalConfirm(p, payment);
		} catch (PayPalRESTException e) {
			throw new PaymentException(e);
		}
		
		
		PaymentTransaction transaction = new PaymentTransaction();
		if (payment.getIntent().equals(PaymentIntent.SALE))
			transaction.setTransactionId(paypalPayment.getTransactions().get(0).getRelatedResources().get(0).getSale().getId());
		else
			transaction.setTransactionId(paypalPayment.getTransactions().get(0).getRelatedResources().get(0).getAuthorization().getId());
		
		transaction.setAmount(new Double(paypalPayment.getTransactions().get(0).getAmount().getTotal()));
		
		transaction.setState(payment.getIntent().equals(PaymentIntent.SALE) ? PaymentTransactionState.COMPLETE :PaymentTransactionState.PENDING);
		
		transaction.setTransactionType((payment.getIntent().equals(PaymentIntent.SALE))?PaymentTransactionType.SALE:PaymentTransactionType.AUTHORIZATION);
		
		transaction.setPayment(payment);
		paymentTransactionRepository.saveAndFlush(transaction);
		
		if (payment.getTransactions() == null)
			payment.setTransactions(new HashSet<PaymentTransaction>());
		
		
		payment.getTransactions().add(transaction);
		
		payment.setAuthorizedAmount(payment.getAuthorizedAmount()+transaction.getAmount());
		if (transaction.getTransactionType().equals(PaymentTransactionType.SALE))
			payment.setCaptureAmount(payment.getCaptureAmount()+transaction.getAmount());
		
		payment.setState(payment.getIntent().equals(PaymentIntent.SALE)?PaymentState.COMPLETED:PaymentState.APPROVED);
		
		paymentRepository.saveAndFlush(payment);
		
		return payment;
		
	}

	@Override
	public Payment capturePayment(Payment payment) throws PaymentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment voidPayment(Payment payment) throws PaymentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment refoundPayment(Payment payment) throws PaymentException {
		// TODO Auto-generated method stub
		return null;
	}

}
