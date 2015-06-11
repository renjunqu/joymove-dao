package com.joymove.util;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.StatefulJob;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.futuremove.cacheServer.entity.Car;
import com.futuremove.cacheServer.service.CarService;
import com.joymove.entity.JOYCar;
import com.joymove.entity.JOYOrder;
import com.joymove.entity.JOYReserveOrder;
import com.joymove.service.JOYCarService;
import com.joymove.service.JOYOrderService;
import com.joymove.service.JOYUserService;
import com.joymove.util.JobUtils;


@DisallowConcurrentExecution
public class CarDriveJob implements StatefulJob   {
	
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
					System.out.println("show map");
					count++;
					args.put("test", count.toString());
					 
					 Iterator iter = args.entrySet().iterator(); 
					 while (iter.hasNext()) { 
					     Map.Entry entry = (Map.Entry) iter.next(); 
					     JSONObject ReObj_POIs_item = new JSONObject();
					     System.out.println("name:"+(String)entry.getKey());
					     System.out.println("value"+(String)entry.getValue());
					 } 
					System.out.println(args.toString());
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
