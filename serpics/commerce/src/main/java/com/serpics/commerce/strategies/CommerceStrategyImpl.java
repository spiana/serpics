package com.serpics.commerce.strategies;

import javax.annotation.Resource;

import com.serpics.base.PriceType;
import com.serpics.commerce.data.model.AbstractOrder;
import com.serpics.commerce.data.model.AbstractOrderitem;
import com.serpics.commerce.data.model.Shipping;
import com.serpics.commerce.services.TaxService;
import com.serpics.core.utils.CurrencyUtils;
import com.serpics.stereotype.StoreStrategy;

@StoreStrategy(value = "commerceStrategy")
public class CommerceStrategyImpl implements CommerceStrategy {

	@Resource(name="taxService")
	TaxService taxService;

	@Override
	public void calculateTax(final AbstractOrder order) {

		double total_price = 0;
		double total_tax = 0;
		double tax = 0;

		if (taxService.findActiveTax(order.getStore()) != null) {
			tax = taxService.findActiveTax(order.getStore()).getRate();

			if (order.getStore().getTaxType() != null || order.getStore().getTaxType() != PriceType.NET) {
				// caso non nullo e calcolo lordo
				total_tax = getGrossTax(total_price, tax);
				total_price = order.getTotalPrice();

				order.setTotalTax(total_tax);
				order.setTotalPrice(total_price - total_tax);
			} else {
				// caso nullo quindi calcolo il prezzo del prodotto netto
				total_tax = getNetTax(total_price, tax);
				total_price = order.getTotalPrice();

				order.setTotalTax(total_tax);
				order.setTotalPrice(total_price + total_tax);
			}
		}
	}

	@Override
	public void calculateShipping(final AbstractOrderitem orderitem) {

		// TODO Auto-generated method stub

	}

	@Override
	public void calculateProductTotal(final AbstractOrder order) {

		double total_netPrice = 0;
		double total_price = 0;

		double total_cost = 0;

		for (final AbstractOrderitem orderItem : order.getItems()) {
			total_netPrice += CurrencyUtils.round(orderItem.getSkuNetPrice() * orderItem.getQuantity());
			total_cost += orderItem.getSkuCost() != null
					? CurrencyUtils.round(orderItem.getSkuCost() * orderItem.getQuantity()) : CurrencyUtils.round(0.0D);
			total_price += orderItem.getSkuPrice() != null
					? CurrencyUtils.round(orderItem.getSkuPrice() * orderItem.getQuantity())
					: CurrencyUtils.round(0.0D);
		}
		order.setTotalProduct(total_netPrice);
		order.setTotalCost(total_cost);
		order.setTotalPrice(total_price);

	}
    @Override
    public void calculateShipping(final AbstractOrder order) {
    	double totalWeight = (double) 0;
    	double selectedValue = (double) 0;
    	for (AbstractOrderitem orderItem : order.getItems()){
    		double productWeight = (double) 0;
    		if (orderItem.getProduct().getWeight() != null){
    			productWeight = orderItem.getProduct().getWeight();
    		}    		
    		totalWeight = productWeight * orderItem.getQuantity();
    	}
    	if (order.getShipmode() != null){
	    	Shipping selectedShipping = new Shipping();
	    	selectedShipping.setValue((double) 0);
	    	selectedShipping.setRangestart((double) 0);    	
	    	for (Shipping shipping : order.getShipmode().getShippings()){
	    		if (shipping.getRangestart() <= totalWeight && shipping.getRangestart() > selectedShipping.getRangestart()){
	    			selectedShipping = shipping;
	    		}
	    	}
	    	selectedValue = selectedShipping.getValue();
    	}
    	order.setTotalShipping(selectedValue);
    }

	@Override
	public void calculateOrderTotal(final AbstractOrder order) {

		double orderAmount = 0;
		orderAmount = orderAmount + getSafeDouble(order.getTotalProduct());
		orderAmount = orderAmount + getSafeDouble(order.getTotalService());
		orderAmount = orderAmount + getSafeDouble(order.getTotalShipping());
		orderAmount = orderAmount + getSafeDouble(order.getTotalTax());

		order.setOrderAmount(orderAmount);

	}

	private double getSafeDouble(Double value) {
		return value != null ? value : 0;
	}

	private double getNetTax(Double taxable, Double tax) {
		// Calcolo dell'iva
		return CurrencyUtils.round(tax * taxable / 100);

	}

	private double getGrossTax(Double taxable, Double tax) {
		// Scorporo dell'iva
		return CurrencyUtils.round(tax * taxable / (100 + tax));

	}
}
