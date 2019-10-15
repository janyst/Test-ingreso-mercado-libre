package com.ar.ml.weather.planet.utils;

import java.text.DecimalFormat;

public class DecimalUtils {
	public static final String TWO_DECIMAL = "#.00";	
	private static final double range = 0.50;
	 
	public static double decimal(double number) {
		return Math.round(number);
	}
	
	public static double decimal(double number, String format) {
		return Double.valueOf(new DecimalFormat(format).format(number).replaceAll(",", "."));
	}
	
	public static boolean between(double numberOne, double numberTwo) {
		Double r = null;
		boolean flag = false;
		r = numberOne - numberTwo;
		if(r>= 0 && r<=range)
			flag = true;
		return flag;
	}
	
}
