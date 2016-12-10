package com.provus.util;

import java.math.BigDecimal;

public class DoubleUtil {
	
	
	public static double cleanExcess(double value, int scale){
		BigDecimal decFra = new BigDecimal(value);
		decFra = decFra.setScale(scale, BigDecimal.ROUND_HALF_UP);
		return decFra.doubleValue();
	}
	
	public static double cleanExcess(double value){
		return cleanExcess(value, 2);
	}

}
