package com.joymove.util;

import static org.quartz.JobBuilder.newJob;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

public class JobUtils {
	
	public static  boolean updateJobDataMap(String name,String group,JobDataMap map) {
		boolean result = true;
		try {
			Scheduler scheduler =  StdSchedulerFactory.getDefaultScheduler();
		    JobKey key = new JobKey(group,name);
		    JobDetail job = scheduler.getJobDetail(key);
		    //if(job!=null) {
		    	JobDataMap argMap = job.getJobDataMap();
		    	argMap.putAll(argMap);
		    	JobDetail jobnew = newJob(CarDriveJob.class)
						.withIdentity(name, group)
						.usingJobData(argMap)
						.storeDurably()
						.build();
		    	scheduler.addJob(jobnew, true);
		   // } else {
		    	result = false;
		   // }
		} catch (Exception e){
			 e.printStackTrace();
			 result = false;
		}
		return result;
	}
	

}
