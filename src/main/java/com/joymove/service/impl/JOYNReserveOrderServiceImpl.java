package com.joymove.service.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYNCarDao;
import com.joymove.entity.JOYNCar;
import org.mongodb.morphia.Datastore;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.futuremove.cacheServer.entity.Car;
import com.futuremove.cacheServer.jobs.ClearExpireReserveJob;
import com.futuremove.cacheServer.service.CarService;
import com.futuremove.cacheServer.service.impl.CarServiceImpl;
import com.futuremove.cacheServer.test.Human;
import com.joymove.dao.JOYReserveOrderDao;
import com.joymove.entity.JOYReserveOrder;
import com.joymove.service.*;
import static org.quartz.JobBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.CalendarIntervalScheduleBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.DateBuilder.*;
import  com.futuremove.cacheServer.jobs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("JOYNReserveOrderService")
public class JOYNReserveOrderServiceImpl implements JOYNReserveOrderService {
	
	
	final static Logger logger = LoggerFactory.getLogger(JOYNReserveOrderServiceImpl.class);
	@Resource(name="carService")
	private CarService cacheCarService;
	@Autowired
	private JOYReserveOrderDao joyReserveOrderDao;
	@Autowired
	private JOYNCarDao joynCarDao;
	@Resource(name="scheduler")
	private Scheduler scheduler;

	
	
	/**********  business proc 
	 * @throws SchedulerException *************/
	
	public void createClearExpireJob(String vinNum,String mobileNo) throws SchedulerException{
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("vinNum", vinNum);
		jobDataMap.put("owner", mobileNo);
		JobDetail job = newJob(ClearExpireReserveJob.class)
				.withIdentity(mobileNo+vinNum, "clearExpire")
				.usingJobData(jobDataMap)
				.build();
		Trigger trigger = newTrigger()
				    .startAt(new Date(System.currentTimeMillis()+60*1000*10))
				    .withSchedule(simpleSchedule()
				    .withIntervalInMinutes(1)
				    .repeatForever())
            		.build();
		 scheduler.scheduleJob(job, trigger);
	}
	


	public boolean insertReserveOrder(Car cacheCar) throws Exception {
		Map<String,Object> likeCondition = new HashMap<String, Object>();
		//if get here, means, not Concurrency exception find
		logger.info("first set the car to pending ok ");
		likeCondition.put("vinNum", cacheCar.getVinNum());
		List<JOYNCar>  ncars = joynCarDao.getNeededCar(likeCondition);
		JOYNCar ncar = ncars.get(0);
		JOYReserveOrder cOrder  = new JOYReserveOrder();
		cOrder.mobileNo = (cacheCar.getOwner());
		cOrder.carVinNum = (cacheCar.getVinNum());
		cOrder.ifBlueTeeth = ncar.ifBlueTeeth;
		logger.info("try to create a new reserve order");
		joyReserveOrderDao.insertNReserveOrder(cOrder);
		logger.info("create new reserve order ok");
		//if not exception here, set a quartz
		//scheduler.sc
		logger.info("try to create clear reserve job");
		createClearExpireJob(cacheCar.getVinNum(), cacheCar.getOwner());
		logger.info("create clear reserve job ok");
		//if not exception here, update the cacheCar state to reserved
		//then return true
		return true;

	}


	public void updateReserveOrderDelFlag(String mobileNo) throws SchedulerException {
		// TODO Auto-generated method stub
		//first get the ReserveOrder from mysql 
		logger.info("find if there is a reserve order is ok");
		Map<String,Object> likeCondition = new HashMap<String, Object>();
		likeCondition.put("mobileNo",mobileNo);
		likeCondition.put("delFlag", JOYReserveOrder.NODEL_FLAG);
		likeCondition.put("expSeconds", 600);
		List<JOYReserveOrder> orders = joyReserveOrderDao.getNeededReserveOrder(likeCondition);
		if(orders.size()>0) {
			logger.info("first remove the reserve order");
			logger.info("find one reserve order");
			JOYReserveOrder cOrder = orders.get(0);
			cOrder.delFlag = (JOYReserveOrder.DEL_FLAG);
			joyReserveOrderDao.updateReserveOrderDelFlag(cOrder);
			logger.info("update car's state is ok,then remove the quartz job");
			//clear the quartz jobs
			JobKey key = new JobKey(mobileNo+cOrder.carVinNum, "clearExpire");
			logger.debug("to be deleted jobs : "+ key.toString());
			scheduler.deleteJob(key);
			logger.info("remove quartz job is ok");
		} else {
			logger.info("not find the reserve order");
		}
		return;
	}
	
	
	public static void main(String []args) {
		Car cacheCar = new Car();
		cacheCar.setOwner("18500217642");
		cacheCar.setVinNum("22222222222222222");
		cacheCar.setState(Car.state_busy);
		
		
		
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:**/applicationContext-mvc.xml");
			Datastore datastore = (Datastore)context.getBean("datastore");
			Scheduler scheduler = (Scheduler)context.getBean("scheduler");
			//scheduler.start();
			datastore.save(cacheCar);
			JOYNReserveOrderService service = (JOYNReserveOrderService)context.getBean("JOYNReserveOrderService");
			System.out.println("get the service ok");
			//service.insertReserveOrder(cacheCar);
			//service.updateReserveOrderDelFlagByMobileNo("18500217642");
			System.out.println("insert new reserve order is ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	
	
	

}
