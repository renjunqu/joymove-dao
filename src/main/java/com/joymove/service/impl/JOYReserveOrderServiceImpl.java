package com.joymove.service.impl;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymove.util.ClearExpireReserveJob;
import com.joymove.service.*;
import com.joymove.dao.*;
import com.joymove.entity.*;

import javax.annotation.Resource;

//import org.springframework.scheduling


@Service("JOYReserveOrderService")
public class JOYReserveOrderServiceImpl  implements JOYReserveOrderService {
	
	
	final static Logger logger = LoggerFactory.getLogger(JOYReserveOrderServiceImpl.class);
	@Autowired
	private JOYReserveOrderDao joyReserveOrderDao;
	@Autowired
	private JOYCarDao   joyCarDao;
	@Resource(name="scheduler")
	private Scheduler scheduler;
	

	
	/*******    business proc   *******/
	
	public void clearExpireReserve(String mobileNo,Integer carId) {
		try {
			// TODO Auto-generated method stub
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("carId", carId.toString());
			jobDataMap.put("owner", mobileNo.toString());
			JobDetail job = newJob(ClearExpireReserveJob.class)
					.withIdentity(mobileNo+carId, "clearExpire")
					.usingJobData(jobDataMap)
					.build();
			Trigger trigger = newTrigger()
					    .startAt(new Date(System.currentTimeMillis()+60*1000*10))
					    .withSchedule(simpleSchedule()
					    .withIntervalInMinutes(1)
					    .repeatForever())
	            		.build();
			 scheduler.scheduleJob(job, trigger);
		} catch(Exception e){
		    e.printStackTrace();	
		}
	}

	
	

	public List<JOYReserveOrder> getNeededReserveOrder(
			Map<String, Object> likeCondition) {
		// TODO Auto-generated method stub
		return joyReserveOrderDao.getNeededReserveOrder(likeCondition);
	}

	
	public void updateCarReserveState(JOYCar car){
		joyCarDao.setCarReserve(car);
	}
	
	
	
	

	public boolean insertReserveOrder(JOYReserveOrder cOrder) throws SchedulerException  {
		// TODO Auto-generated method stub
		
		cOrder.delFlag = (JOYReserveOrder.DEL_FLAG);
		//this.updateReserveOrderDelFlagByMobileNo(cOrder);
		//clear others 
		 Map<String,Object> likeCondition = new HashMap<String, Object>();
		 likeCondition.put("mobileNo", cOrder.mobileNo);
		 likeCondition.put("delFlag", JOYReserveOrder.NODEL_FLAG);
		 likeCondition.put("expSeconds", JOYReserveOrder.EXPIRE_SECONDS);
		 //remove the old reserve
		 List<JOYReserveOrder> reOrders = joyReserveOrderDao.getNeededReserveOrder(likeCondition);
		 for(JOYReserveOrder order:reOrders) {
			 this.updateReserveOrderDelFlag(order);
		 }
		 //start to reserve the new
		JOYCar car = new JOYCar();
		car.id = (cOrder.carId);
        car.state = (JOYCar.STATE_RESERVE);
        car.mobileNo = (cOrder.mobileNo);
        joyCarDao.setCarReserve(car);
		
		//set a timer to clear the reserve info
		likeCondition.put("id", car.id);
		List<JOYCar> cars = joyCarDao.getCarById(likeCondition);
		car = cars.get(0);
		if(cOrder.mobileNo.equals(car.mobileNo)) {
			cOrder.delFlag= (JOYReserveOrder.NODEL_FLAG);
			joyReserveOrderDao.insertReserveOrder(cOrder);
			/*start a delay task*/
			clearExpireReserve(car.mobileNo,car.id);
			return true;
		} else {
			return false;
		}
			
	}

	

	public void updateReserveOrderDelFlag(JOYReserveOrder cOrder) throws SchedulerException {
		
		
				
				cOrder.delFlag = (JOYReserveOrder.DEL_FLAG);
				joyReserveOrderDao.updateReserveOrderDelFlag(cOrder);
				//update car 's state
				JOYCar car = new JOYCar();
				car.mobileNo = (cOrder.mobileNo );
				car.id = (cOrder.carId); //getCarId());
				joyCarDao.clearReserve(car);
				//delete the job
				JobKey key = new JobKey(cOrder.mobileNo+cOrder.carId, "clearExpire");
				logger.debug("to be deleted jobs : "+ key.toString());
				scheduler.deleteJob(key);
	}

}
