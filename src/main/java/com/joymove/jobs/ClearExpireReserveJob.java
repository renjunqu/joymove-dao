package com.joymove.jobs;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.joymove.dao.JOYCarDao;
import com.joymove.entity.JOYCar;

public class ClearExpireReserveJob  implements Job {
	
	final static Logger logger = LoggerFactory.getLogger(ClearExpireReserveJob.class);
	
	/************  business proc  ***********************/
	
	
	
	

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			Map args = arg0.getJobDetail().getJobDataMap();
			String carId = (String)args.get("carId");
			String owner = (String)args.get("owner");
	
			//ApplicationContext context = new ClassPathXmlApplicationContext("classpath:**/applicationContext-bean.xml");
			WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
			Scheduler scheduler = (Scheduler)context.getBean("scheduler");
			JobKey jobKey = new JobKey(owner + carId, "clearExpire");
			JOYCarDao joyCarDao = (JOYCarDao)context.getBean("JOYCarDao");
		    //update the car's state
			JOYCar car = new JOYCar();
			car.mobileNo = (owner);
			car.id = (Integer.parseInt(carId));
			joyCarDao.clearReserve(car);
			logger.info("clear expire timer running ......... for fake car: "+carId+"......");
			//delete the job
			scheduler.deleteJob(jobKey);
		} catch(Exception e){
			logger.debug("exception happens when clear expire for fake car ");
			logger.debug(e.toString());
			//logger.debug(e.printStackTrace());
			logger.debug("show exception over");
		}
		//logger.trace("Job executing: "+name + " ****** Date: "+new Date());
	}

	public static  void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:**/applicationContext-mvc.xml");
		JOYCarDao joyCarDao = (JOYCarDao)context.getBean("JOYCarDao");
		logger.trace("sdfsdf");
	}
}
