package com.ar.ml.weather.planet.jobs;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ar.ml.weather.planet.application.ServiceRest;
import com.ar.ml.weather.planet.service.Factory;
import com.ar.ml.weather.planet.service.FactoryImpl;

public class WeatherJob implements Job{
	private final int YEARS = 365*10;
	public static boolean executeJbo;
			
	private FactoryImpl factoryImpl = Factory.getFactory();	
	static Logger logger = Logger.getLogger(ServiceRest.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		if(factoryImpl.weatherTenYears(YEARS)) {
			Factory.setExecuteJob(true);
			logger.info("se ejecuto correctamente el jobs, ya queda instanceado 1 ejecucion por dia");
		}else {
			logger.info("no se puedo ejecutar el jobs, se intentara nuevamente en una nueva solicitud o el dia de mañana");
		}
	}

}
