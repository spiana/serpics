package com.serpics.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyUtils {

	
	public static Double round(Double value){
		if (value == null)
			value = 0.0D;
		
		 return new BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	}
}
