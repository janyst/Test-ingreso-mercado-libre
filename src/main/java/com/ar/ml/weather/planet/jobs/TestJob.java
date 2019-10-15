package com.ar.ml.weather.planet.jobs;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class TestJob {

	public static void main(String[] args) {
		JobDetail job = JobBuilder.newJob(WeatherJob.class).build();//tarea		
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 0 ? * * *");//time
		Trigger trigger = TriggerBuilder.newTrigger().withSchedule(scheduleBuilder).build();
		
		Scheduler scheduler;
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
