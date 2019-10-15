package com.ar.ml.weather.planet.application;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.ar.ml.weather.planet.service.Factory;
import com.ar.ml.weather.planet.service.FactoryImpl;

@Path("/weather") 

public class ServiceRest {
	static Logger logger = Logger.getLogger(ServiceRest.class);
	
	private static FactoryImpl factoryImpl = Factory.getFactory();
	
	@GET
	@Path("/state") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatuDay(@QueryParam("dia") String days) { 
		logger.info("peticion getStatuDay, dia: "+days);
		return factoryImpl.stateDay(days);
    } 
	
	@GET
	@Path("/summary") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSummaryWeather(@QueryParam("civilizacion") String namePlanet) { 		
		logger.info("peticion getSummaryWeather, nombre planeta: "+namePlanet);
		return factoryImpl.summaryDay(namePlanet);
    } 
}
