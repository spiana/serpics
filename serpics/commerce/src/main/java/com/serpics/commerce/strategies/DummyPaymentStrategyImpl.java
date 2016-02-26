package com.serpics.commerce.strategies;

import java.util.HashSet;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.serpics.commerce.PaymentException;
import com.serpics.commerce.PaymentIntent;
import com.serpics.commerce.PaymentState;
import com.serpics.commerce.PaymentTransactionState;
import com.serpics.commerce.PaymentTransactionType;
import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.commerce.data.model.Payment;
import com.serpics.commerce.data.model.PaymentTransaction;
import com.serpics.commerce.data.repositories.PaymentRepository;
import com.serpics.commerce.data.repositories.PaymentTransactionRepository;
import com.serpics.stereotype.StoreStrategy;


@StoreStrategy("dummyPayment")
public class DummyPaymentStrategyImpl implements PaymentStrategy {
	
	Logger LOG = LoggerFactory.getLogger(DummyPaymentStrategyImpl.class);

	@Resource
	PaymentRepository paymentRepository ;
	
	@Resource
	PaymentTransactionRepository paymentTransactionRepository;
	
	@Override
	public Payment createPayment(AbstractOrder order , PaymentIntent intent)  throws PaymentException{
		Assert.notNull(order , "order can not be null !");
		
		Payment payment = new Payment();
		payment.setAmount(order.getOrderAmount());
		payment.setPaymethod(order.getPaymethod());
		payment.setIntent(intent);
		payment.setState(PaymentState.CREATED);
		payment.setCurrency(order.getCurrency());
		payment.setAuthorizedAmount(0D);
		payment.setCaptureAmount(0D);
		payment.setRefoundAmount(0D);
		payment.setOrder(order);
		payment.setPaymentIdentifier(order.getId().toString());
		paymentRepository.saveAndFlush(payment);
		
		return payment;
		
	}
		
	
	@Override
	public Payment authorizePayment(Payment payment)  throws PaymentException {
		Assert.notNull(payment);
		
		PaymentTransaction transaction = new PaymentTransaction();
		transaction.setTransactionId(UUID.randomUUID().toString());
		transaction.setAmount(payment.getAmount());
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
	public Payment capturePayment(Payment payment)  throws PaymentException {
		
			if(!payment.getState().equals(PaymentState.APPROVED)){
				LOG.warn("payment {} state is not correct !" , payment.getPaymentIdentifier());
				return payment;
			}
			
			for (PaymentTransaction transaction : payment.getTransactions()) {
				LOG.info("found transaction {}  type {} " , transaction.getTransactionId() , transaction.getTransactionType());
				if (transaction.getTransactionType().equals(PaymentTransactionType.AUTHORIZATION) &&
						transaction.getState().equals(PaymentTransactionState.PENDING)){
					
					PaymentTransaction newtransaction = new PaymentTransaction();
					newtransaction.setTransactionId(UUID.randomUUID().toString());
					newtransaction.setAmount(payment.getAmount());
					newtransaction.setState(PaymentTransactionState.COMPLETE);
					newtransaction.setTransactionType((payment.getIntent().equals("sale"))?PaymentTransactionType.SALE:PaymentTransactionType.AUTHORIZATION);
					newtransaction.setPayment(payment);
					transaction.setState(PaymentTransactionState.COMPLETE);
					newtransaction.setTransaction(transaction);
					
					paymentTransactionRepository.saveAndFlush(newtransaction);
					
					if (payment.getTransactions() == null)
						payment.setTransactions(new HashSet<PaymentTransaction>());
					
					payment.getTransactions().add(newtransaction);
					
					payment.setState(PaymentState.COMPLETED);
					payment.setCaptureAmount(payment.getCaptureAmount()+transaction.getAmount());
					
					paymentRepository.saveAndFlush(payment);
					LOG.info("transaction {} captured " , transaction.getTransactionId());
				}else
					LOG.info("Transaction {} not camptured because state is {} " , transaction.getTransactionId() , transaction.getState());
			}
			return payment;
	}

	@Override
	public Payment voidPayment(Payment payment)  throws PaymentException{

		for (PaymentTransaction transaction : payment.getTransactions()) {
			if (transaction.getTransactionType().equals(PaymentTransactionType.AUTHORIZATION) &&
					transaction.getState().equals(PaymentTransactionState.PENDING)){
				
				transaction.setState(PaymentTransactionState.VOIDED);

				paymentTransactionRepository.saveAndFlush(transaction);

				PaymentTransaction newtransaction = new PaymentTransaction();
				newtransaction.setTransactionId(UUID.randomUUID().toString());
				newtransaction.setAmount(payment.getAmount());
				newtransaction.setState(PaymentTransactionState.COMPLETE);
				newtransaction.setTransactionType(PaymentTransactionType.VOID);
				newtransaction.setPayment(payment);
				transaction.setState(PaymentTransactionState.COMPLETE);
				newtransaction.setTransaction(transaction);
				
				paymentTransactionRepository.saveAndFlush(newtransaction);
				
				if (payment.getTransactions() == null)
					payment.setTransactions(new HashSet<PaymentTransaction>());
				
				payment.getTransactions().add(newtransaction);
				
				payment.setState(PaymentState.COMPLETED);
				payment.setAuthorizedAmount(payment.getAuthorizedAmount()-transaction.getAmount());
				
				paymentRepository.saveAndFlush(payment);
				
			
			}
		}
		return payment;
	}

	@Override
	public Payment refoundPayment(Payment payment) throws PaymentException {
		return null;
		
	}

	

}
