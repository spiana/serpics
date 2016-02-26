package com.serpics.paypal;

import java.io.InputStream;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;

import com.paypal.api.payments.Payment;

public class InitializePayment implements InitializingBean{

	private String propertyFile;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		InputStream is = InitializePayment.class.getResourceAsStream(propertyFile);
		Payment.initConfig(is);
	}

	@Required
	public void setPropertyFile(String propertyFile) {
		this.propertyFile = propertyFile;
	}

}
