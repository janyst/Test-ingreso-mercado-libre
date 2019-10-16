package com.ar.ml.weather.planet.service;

import com.ar.ml.weather.planet.helper.WeatherPlanetHelper;
import com.ar.ml.weather.planet.model.Planet;
import com.ar.ml.weather.planet.utils.Constant;

public class Factory {
	public static FactoryImpl factoryImpl;
	public static boolean executeJob;
	
	public static FactoryImpl getFactory() {
		if(factoryImpl == null) {
			factoryImpl = new FactoryImpl();			
			factoryImpl.setWeatherPlanetHelper(new WeatherPlanetHelper());
			factoryImpl.getWeatherPlanetHelper().setVulcano(new Planet(Constant.PLANET_VULCANO, Constant.MOVEMENT_FOR_DAY_VULCANO, Constant.TYPE_MOVENT_POS,Constant.RADIO_VULCANO));
			factoryImpl.getWeatherPlanetHelper().setFeregi(new Planet(Constant.PLANET_FEREGI, Constant.MOVEMENT_FOR_DAYL_FEREGI, Constant.TYPE_MOVENT_NEG, Constant.RADIO_FEREGI));
			factoryImpl.getWeatherPlanetHelper().setBetosoide(new Planet(Constant.PLANET_BETOSOIDE, Constant.MOVEMENT_FOR_DAY_BETOSOIDE, Constant.TYPE_MOVENT_NEG, Constant.RADIO_BETOSOIDE));
			
		}
		return factoryImpl;
	}

	public static boolean isExecuteJob() {
		return executeJob;
	}

	public static void setExecuteJob(boolean executeJob) {
		Factory.executeJob = executeJob;
	}
	
}
