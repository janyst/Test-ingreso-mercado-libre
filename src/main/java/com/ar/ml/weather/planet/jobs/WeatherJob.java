package com.ar.ml.weather.planet.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ar.ml.weather.planet.service.Factory;
import com.ar.ml.weather.planet.service.FactoryImpl;

public class WeatherJob implements Job{
	private FactoryImpl factoryImpl = Factory.getFactory();
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		
	}

}
