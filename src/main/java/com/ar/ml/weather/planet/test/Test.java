package com.ar.ml.weather.planet.test;

import com.ar.ml.weather.planet.helper.WeatherPlanetHelper;
import com.ar.ml.weather.planet.model.Planet;
import com.ar.ml.weather.planet.model.SummaryDay;
import com.ar.ml.weather.planet.utils.Constant;


public class Test {
	public static void main(String[] args) {
		WeatherPlanetHelper weatherPlanetHelper = new WeatherPlanetHelper();
		weatherPlanetHelper.setVulcano(new Planet(Constant.PLANET_VULCANO, Constant.MOVEMENT_FOR_DAY_VULCANO, Constant.TYPE_MOVENT_POS,Constant.RADIO_VULCANO));
		weatherPlanetHelper.setFeregi(new Planet(Constant.PLANET_FEREGI, Constant.MOVEMENT_FOR_DAYL_FEREGI, Constant.TYPE_MOVENT_NEG, Constant.RADIO_FEREGI));
		weatherPlanetHelper.setBetosoide(new Planet(Constant.PLANET_BETOSOIDE, Constant.MOVEMENT_FOR_DAY_BETOSOIDE, Constant.TYPE_MOVENT_NEG, Constant.RADIO_BETOSOIDE));
		
		System.out.println(90%360);
		for (int i = 1; i < 1000; i++) {
			weatherPlanetHelper.getVulcano().getAngule(i);		
			weatherPlanetHelper.getFeregi().getAngule(i);
			weatherPlanetHelper.getBetosoide().getAngule(i);
			
			SummaryDay summaryDay = weatherPlanetHelper.typeWeather(i);
			if(summaryDay.getTypeWeather().equalsIgnoreCase(Constant.TYPE_WEATHER_DROUGHT)
					|| summaryDay.getTypeWeather().equalsIgnoreCase(Constant.TYPE_WEATHER_PERFECT)
					|| summaryDay.getTypeWeather().equalsIgnoreCase(Constant.TYPE_WEATHER_RAIN)) {
				System.out.println("INDEX: "+i+" tipo "+summaryDay.getTypeWeather());
				System.out.println(weatherPlanetHelper.typeWeather(i));
				System.out.println("getVulcano "+weatherPlanetHelper.getVulcano().getAngule()+" X "+weatherPlanetHelper.getVulcano().getX()+" Y "+weatherPlanetHelper.getVulcano().getY());
				System.out.println("getFeregi "+weatherPlanetHelper.getFeregi().getAngule()+" X "+weatherPlanetHelper.getFeregi().getX()+" Y "+weatherPlanetHelper.getFeregi().getY());
				System.out.println("getBetosoide "+weatherPlanetHelper.getBetosoide().getAngule()+" X "+weatherPlanetHelper.getBetosoide().getX()+" Y "+weatherPlanetHelper.getBetosoide().getY());
			}
			
		}
	}

}
