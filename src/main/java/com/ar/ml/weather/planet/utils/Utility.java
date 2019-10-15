package com.ar.ml.weather.planet.utils;

import org.apache.log4j.Logger;

public class Utility {
	private static Logger logger = Logger.getLogger(Utility.class);
	
	public static int validateNumber(String value) {
		int valorReturn = 0;
		try {
			int number = Integer.parseInt(value);
			if(number > 0)
				valorReturn = number;
		}catch (Exception e) {
			logger.error("el valor ingresado no es el esperado.");
		}
		return valorReturn;
	}
	
	public static boolean validateNamePlanet(String value) {
		boolean flag = false;
		if(value != null && (value.equalsIgnoreCase(Constant.PLANET_VULCANO) || value.equalsIgnoreCase(Constant.PLANET_FEREGI) || value.equalsIgnoreCase(Constant.PLANET_BETOSOIDE)))
			flag = true;
		return flag;
	}

}
