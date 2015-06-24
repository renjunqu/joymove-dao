package com.joymove.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;


import com.joymove.dao.JOYBaseDao;
import com.joymove.dao.JOYUserDao;
import com.joymove.entity.*;
import com.joymove.service.*;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.math.BigDecimal;
@Service("JOYUserService")
public class JOYUserServiceImpl extends JOYBaseServiceImpl<JOYUser> implements  JOYUserService {
	
	final static Logger logger = LoggerFactory.getLogger(JOYUserServiceImpl.class);
	@Autowired
	private JOYUserDao joyUserDao;


	public JOYBaseDao getBaseDao(){
		return joyUserDao;
	}

	public Class<JOYUser> getEntityClass(){
		return JOYUser.class;
	}

	public String checkUserState(JOYUser user) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String errMsg = null;
		logger.warn("inside get joy user info ");
		List<JOYUser> users = this.getNeededList(user);
		JOYUser currUser = users.get(0);
		if( currUser.authenticateId !=JOYUser.auth_state_ok ) {
			errMsg = "用户身份认证未通过";
		} else if(currUser.authenticateDriver != JOYUser.auth_state_ok ) {
			errMsg = "用户驾照认证未通过";
		} else if(currUser.deposit.doubleValue()  < 0.01){
			errMsg = "用户押金余额不足";
		} else if(currUser.id5PassFlag!=JOYUser.auth_state_ok) {
			errMsg="用户未通过ID5认证";
		}
		return errMsg;
	}






	public static void main(String [] args) throws  Exception {

		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:test.xml");
        Map<String,Object> likeCondition = new HashMap<String, Object>();
		JOYUserDao   dao = (JOYUserDao)context.getBean("JOYUserDao");
		List<Map<String,Object>> mapList = dao.test(likeCondition);
		for(int i=0;i<mapList.size();i++) {
			JOYUser user = new JOYUser();
			user.fromMap(mapList.get(i));
			System.out.println(user);
		}



		/*


		JOYPayHistoryService service = (JOYPayHistoryService)context.getBean("JOYPayHistoryService");
		JOYPayHistory payHistoryNew = new JOYPayHistory();
		payHistoryNew.balance = 0.2;
		payHistoryNew.type = 2;
		service.deleteByProperties(payHistoryNew);


		/*
		JOYUserService service = (JOYUserService)context.getBean("JOYUserService");
		JOYUser user = new JOYUser();
		user.mobileNo = "18500217642";
		List<Map<String,Object>> mapList = service.getExtendInfoPagedList(" select u.*, m.driverLicenseNumber  from JOY_Users u left join JOY_DriverLicense m on u.mobileNo = m.mobileNo ",user);


	//	JOYUser user2 = new JOYUser();
		Map<String,Object> t = mapList.get(0);
		Iterator i =t.entrySet().iterator();
		JSONObject tt = new JSONObject();
		while(i.hasNext()) {

			Map.Entry<String,Object> haha = (Map.Entry<String,Object>)i.next();
		    if(String.valueOf(haha.getValue()).equals("null")) {
				logger.trace(haha.getKey()+" is null");
			}
		}

/*
		user2.username = "曲仁军";
		user.mobileNo="18500217642";
		service.updateRecord(user2,user);
		user = service.getNeededRecord(user);
        logger.trace(user);

		/*
		JOYOrderService service = (JOYOrderService) context.getBean("JOYOrderService");
	   JOYOrder order = new JOYOrder();
		order = service.getNeededRecord(order);
		JOYOrder order2 = new JOYOrder();
		order2.startTime = order.startTime;
		order = new JOYOrder();
		order.startTime = new Date(System.currentTimeMillis());
	   service.updateRecord(order,order2);
			/*
		JOYNReserveOrderService  service  = (JOYNReserveOrderService)context.getBean("JOYNReserveOrderService");
		JOYReserveOrder order = new JOYReserveOrder();
		//service.insertRecord(order);
		JOYReserveOrder order2 = new JOYReserveOrder();
		order2.mobileNo = "18500217642";
		order2.startTime = new Date(System.currentTimeMillis());
        service.insertRecord(order2);
		order2.startTime = null;
		order = service.getNeededRecord(order2);
	    order.startTime = new Date(System.currentTimeMillis());
		service.updateRecord(order,order2);
        order2.startTime = order.startTime;
		order2.mobileNo = null;
		order = service.getNeededRecord(order2);
		logger.trace(order);
		/*
		order.delFlag = 1;
		order.startTime = new Date(System.currentTimeMillis()+30);
		service.updateRecord(order,order2);

		order2.mobileNo = null;
		order2.startTime = order.startTime;
		order = service.getNeededRecord(order2);
		logger.trace(order);
		//service.deleteByProperties(order);


		/*
		JOYIdAuthInfoService service  = (JOYIdAuthInfoService)context.getBean("JOYIdAuthInfoService");
		JOYIdAuthInfo dl = new JOYIdAuthInfo();
		dl.idAuthInfo = "nihao".getBytes();
		dl.idAuthInfo_back = "Hello world".getBytes();
		JOYIdAuthInfo dl2 = new JOYIdAuthInfo();
		dl2.mobileNo = "15577586649";
		service.updateRecord(dl,dl2);


		service.getNeededList(dl2,null,null);



		List<JOYIdAuthInfo> dList = service.getNeededList(dl,null,null);
		logger.trace(dList.get(0));

		for(int i=0;i<dList.get(0).idAuthInfo.length;i++)
		System.out.format("%c",dList.get(0).idAuthInfo[i]);
/*
		JOYUser user = new JOYUser();
		JOYUser user1 = new JOYUser();
		user.mobileNo = ("18500217642");
		List<JOYUser> userList =  service.getNeededList(user,0,10);
        logger.trace("sdfdsdsf :"+userList.size());
		JOYUser u = userList.get(0);
		logger.trace(u);
	    */
	}


}
