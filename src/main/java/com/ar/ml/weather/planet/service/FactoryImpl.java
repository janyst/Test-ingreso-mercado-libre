package com.ar.ml.weather.planet.service;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.ar.ml.weather.planet.dto.ConsultResponseDTO;
import com.ar.ml.weather.planet.dto.SummaryDTO;
import com.ar.ml.weather.planet.helper.WeatherPlanetHelper;
import com.ar.ml.weather.planet.model.Planet;
import com.ar.ml.weather.planet.model.SummaryDay;
import com.ar.ml.weather.planet.utils.Constant;
import com.ar.ml.weather.planet.utils.Utility;



public class FactoryImpl {
	static Logger logger = Logger.getLogger(FactoryImpl.class);
	
	private WeatherPlanetHelper weatherPlanetHelper; 
	
	/**
	* <h1>retorna un objeto Response</h1>
	* calcula el clima que se presentara en el dia. 
	* @author A704945
	*/	
	public Response stateDay(String days) {
		int daysValidate;
		ConsultResponseDTO conDto = null;
		Status status = null;
		SummaryDay summaryDay = null;		
		
		daysValidate = Utility.validateNumber(days);		
		if(daysValidate == 0) {
			conDto = new ConsultResponseDTO(daysValidate, Constant.ERROR_MESSAGE_BAD_REQUEST);
			status = Response.Status.BAD_REQUEST;
			logger.warn("el dia es incorrecto, se reporte en el body de la respuesa, dato ingresado por el user: "+days);
		}else {
			summaryDay = weatherPlanetHelper.typeWeather(daysValidate);
			conDto = new ConsultResponseDTO(daysValidate, summaryDay.getDescriptionTypeWeather());
			status = Response.Status.OK;
			logger.info("Respuesta OK, "+summaryDay.toString());
		}
		return Response.status(status).entity(conDto).build();
	}
	
	/**
	* <h1>retorna el resumen de la corrida del jobs.</h1>
	* busca los datos almacenados en BD de la corrida generada por 10 años.
	* @author A704945
	*/
	public Response summaryDay(String namePlanet) {
		Status status = null;
		SummaryDTO summaryDTO = null;
		String msg = null;
			
		if(!Utility.validateNamePlanet(namePlanet)) {
			status = Response.Status.UNAUTHORIZED;
			summaryDTO = new SummaryDTO(1, Constant.ERROR_MESSAGE_UNAUTHORIZED);
			logger.info("planeta no autorizado "+namePlanet);
		}else {
			status = Response.Status.OK;
			summaryDTO = weatherPlanetHelper.summaryTenYears();
			msg = "Bienvenido "+namePlanet.toUpperCase()+" "+Constant.MESSAGE_OK_RAIN+" "+summaryDTO.getMaxDayRain().getDay();
			summaryDTO.setMsg(msg);
			logger.info("Respuesta OK, "+summaryDTO.toString());
		}
		return Response.status(status).entity(summaryDTO).build();
	}
	
	/**
	* <h1>busca el clima de 10 años.</h1>
	* @author A704945
	*/
	public void jobsTenYears(int days) {
		Status status = null;
		SummaryDTO summaryDTO = null;
		String msg = null;
	}
	
	public WeatherPlanetHelper getWeatherPlanetHelper() {
		return weatherPlanetHelper;
	}

	public void setWeatherPlanetHelper(WeatherPlanetHelper weatherPlanetHelper) {
		this.weatherPlanetHelper = weatherPlanetHelper;
	}
}
