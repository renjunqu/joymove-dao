package com.joymove.util;

import static org.quartz.JobBuilder.newJob;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.StatefulJob;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joymove.service.JOYCarService;
import com.joymove.service.JOYOrderService;
import com.joymove.service.JOYUserService;


@DisallowConcurrentExecution
public class CarDriveJob implements StatefulJob   {

	final static Logger logger = LoggerFactory.getLogger(CarDriveJob.class);



	//car service
	private JOYCarService joyCarService;
	//user service
	private JOYUserService joyUserService;
	//order service 
	private JOYOrderService joyOrderService;
	
	public static int carSpeed = 10;// 10m/s
	public static Integer count = 1;
	
	/********* **************/


	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Map<String,Object> likeCondition = new HashMap<String, Object>();
				try {
					
					JobDataMap args = arg0.getJobDetail().getJobDataMap();
					/*
					String carId = (String)args.get("carId");
					String mobileNo = (String)args.get("mobileNo");
					 //first get the order
					 likeCondition.put("mobileNo", mobileNo);
					 likeCondition.put("delMark", JOYReserveOrder.NODEL_FLAG);
					 likeCondition.put("id", carId);
					 List<JOYOrder> orders = joyOrderService.getNeededOrder(likeCondition);
					 JOYOrder cOrder = orders.get(0);
					 List<JOYCar> cars = joyCarService.getCarById(likeCondition);
					 JOYCar cCar = cars.get(0);
					 */
					logger.trace("show map");
					count++;
					args.put("test", count.toString());
					 
					 Iterator iter = args.entrySet().iterator(); 
					 while (iter.hasNext()) { 
					     Map.Entry entry = (Map.Entry) iter.next(); 
					     JSONObject ReObj_POIs_item = new JSONObject();
					     logger.trace("name:"+(String)entry.getKey());
					     logger.trace("value"+(String)entry.getValue());
					 } 
					logger.trace(args.toString());
				} catch(Exception e){
				
				}
				
		
	}
	
	public static void main(String[] args){
		 //ApplicationContext context = new ClassPathXmlApplicationContext("classpath:**/applicationContext-mvc.xml");
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			// TODO Auto-generated method stub
			
			
		
			 scheduler.start();
      } catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		 
	}
	
	

}
