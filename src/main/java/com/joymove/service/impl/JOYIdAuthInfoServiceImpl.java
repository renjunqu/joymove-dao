package com.joymove.service.impl;

import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYIdAuthInfoDao;
import com.joymove.dao.JOYUserDao;
import com.joymove.entity.JOYIdAuthInfo;
import com.joymove.entity.JOYUser;
import com.joymove.service.JOYIdAuthInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("JOYIdAuthInfoService")
public class JOYIdAuthInfoServiceImpl implements JOYIdAuthInfoService{
	@Autowired
	private JOYIdAuthInfoDao joyIdAuthInfoDao;
	@Autowired
	private JOYUserDao joyUserDao;

	

	

	/****  public proc  **********/
	

	public void insertIdAuthInfo(JOYIdAuthInfo authInfo) {
		// TODO Auto-generated method stub
		JOYUser user = new JOYUser();
		user.mobileNo = authInfo.mobileNo;
		user.authenticateId = JOYUser.auth_state_ing;
		joyIdAuthInfoDao.insertIdAuthInfo(authInfo);
		joyUserDao.updateJOYUser(user);
	}


	public List<JOYIdAuthInfo> getNeededIdAuthInfo(Map<String, Object> likeCondition) {
		// TODO Auto-generated method stub
		return joyIdAuthInfoDao.getNeededIdAuthInfo(likeCondition);
	}

	


	public void updateIdAuthInfo(JOYIdAuthInfo authInfo) {
		// TODO Auto-generated method stub
		JOYUser user = new JOYUser();
		user.mobileNo = authInfo.mobileNo;
		user.authenticateId = JOYUser.auth_state_ing;
		joyIdAuthInfoDao.updateIdAuthInfo(authInfo);
		joyUserDao.updateJOYUser(user);
	}
	
	
	
	

}
