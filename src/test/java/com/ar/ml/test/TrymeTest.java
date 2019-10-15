package com.ar.ml.test;

import org.junit.Assert;
import org.junit.Test;

import com.ar.ml.weather.planet.helper.WeatherPlanetHelper;
import com.ar.ml.weather.planet.model.Planet;
import com.ar.ml.weather.planet.model.SummaryDay;
import com.ar.ml.weather.planet.utils.Constant;


public class TrymeTest {
	@Test
	public void droughtTest() {
		int days = 90;
		WeatherPlanetHelper weatherPlanetHelper = new WeatherPlanetHelper();
		weatherPlanetHelper.setVulcano(new Planet(Constant.PLANET_VULCANO, Constant.MOVEMENT_FOR_DAY_VULCANO, Constant.TYPE_MOVENT_POS,Constant.RADIO_VULCANO));
		weatherPlanetHelper.setFeregi(new Planet(Constant.PLANET_FEREGI, Constant.MOVEMENT_FOR_DAYL_FEREGI, Constant.TYPE_MOVENT_NEG, Constant.RADIO_FEREGI));
		weatherPlanetHelper.setBetosoide(new Planet(Constant.PLANET_BETOSOIDE, Constant.MOVEMENT_FOR_DAY_BETOSOIDE, Constant.TYPE_MOVENT_NEG, Constant.RADIO_BETOSOIDE));
		
		weatherPlanetHelper.getVulcano().getAngule(days);		
		weatherPlanetHelper.getFeregi().getAngule(days);
		weatherPlanetHelper.getBetosoide().getAngule(days);
		
		SummaryDay summaryDay = weatherPlanetHelper.typeWeather(days);
		Assert.assertEquals(summaryDay.getTypeWeather(),Constant.TYPE_WEATHER_DROUGHT);
	}
	
	@Test
	public void rainTest() {
		int days = 28;
		WeatherPlanetHelper weatherPlanetHelper = new WeatherPlanetHelper();
		weatherPlanetHelper.setVulcano(new Planet(Constant.PLANET_VULCANO, Constant.MOVEMENT_FOR_DAY_VULCANO, Constant.TYPE_MOVENT_POS,Constant.RADIO_VULCANO));
		weatherPlanetHelper.setFeregi(new Planet(Constant.PLANET_FEREGI, Constant.MOVEMENT_FOR_DAYL_FEREGI, Constant.TYPE_MOVENT_NEG, Constant.RADIO_FEREGI));
		weatherPlanetHelper.setBetosoide(new Planet(Constant.PLANET_BETOSOIDE, Constant.MOVEMENT_FOR_DAY_BETOSOIDE, Constant.TYPE_MOVENT_NEG, Constant.RADIO_BETOSOIDE));
		
		weatherPlanetHelper.getVulcano().getAngule(days);		
		weatherPlanetHelper.getFeregi().getAngule(days);
		weatherPlanetHelper.getBetosoide().getAngule(days);
		
		SummaryDay summaryDay = weatherPlanetHelper.typeWeather(days);
		Assert.assertEquals(summaryDay.getTypeWeather(),Constant.TYPE_WEATHER_RAIN);
	}

}
