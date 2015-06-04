package com.joymove.service.impl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYNCarDao;
import com.joymove.entity.JOYNCar;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.futuremove.cacheServer.entity.Car;
import com.futuremove.cacheServer.service.CarService;
import com.futuremove.cacheServer.utils.ConfigUtils;
import com.futuremove.cacheServer.utils.HttpPostUtils;
import com.joymove.dao.JOYOrderDao;
import com.joymove.dao.JOYReserveOrderDao;
import com.joymove.entity.JOYOrder;
import com.joymove.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("JOYNOrderService")
public class JOYNOrderServiceImpl implements JOYNOrderService {
	
	final static Logger logger = LoggerFactory.getLogger(JOYNOrderServiceImpl.class);
	@Resource(name="carService")
	private CarService      cacheCarService;
	@Autowired
	private JOYReserveOrderDao joyReserveOrderDao;
	@Autowired
	private JOYOrderDao        joyOrderDao;
	@Resource(name="scheduler")
	private Scheduler scheduler;
	@Autowired
	private JOYNCarDao joynCarDao;
	
	



	public List<JOYOrder> getNeededOrder(Map<String, Object> likeCondition) {
		// TODO Auto-generated method stub
		return joyOrderDao.getNeededOrder(likeCondition);
	}







	public void updateOrderTermiate(Car car) throws  Exception {
		// TODO Auto-generated method stub
		 //goto order pay work flow
		Map<String,Object> likeCondition = new HashMap<String, Object>();
		likeCondition.put("carVinNum", car.getVinNum());
		likeCondition.put("mobileNo", car.getOwner());
		likeCondition.put("delMark", JOYOrder.NON_DEL_MARK);
		List<JOYOrder> orders = joyOrderDao.getNeededOrder(likeCondition);
		JOYOrder cOrder = orders.get(0);
		cOrder.stopLatitude = car.getLatitude();
		cOrder.stopLongitude = car.getLongitude();
		cOrder.state = (JOYOrder.state_wait_pay);
		cOrder.stopTime = (new Date(System.currentTimeMillis()));
		joyOrderDao.updateNOrderStop(cOrder);
	}


	public void insertNOrder(JOYOrder order) throws SchedulerException {
		//clear the quartz job of this people and this car vinnum
		JobKey key = new JobKey(order.mobileNo +order.carVinNum, "clearExpire");
		logger.debug("to be deleted jobs : "+ key.toString());
		scheduler.deleteJob(key);
		logger.info("remove quartz job is ok");
		joyOrderDao.insertNOrder(order);
	}


	public void changeNBatonMode(JOYOrder cOrder) {
		// TODO Auto-generated method stub
		joyOrderDao.changeNBatonMode(cOrder);
		
	}


	public void updateNDestination(JOYOrder cOrder) {
		// TODO Auto-generated method stub
		joyOrderDao.updateNDestination(cOrder);
	}
		
	/**********  business proc *************/

}
