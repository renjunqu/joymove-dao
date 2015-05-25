package com.joymove.service.impl;

import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYIdAuthInfoDao;
import com.joymove.entity.JOYIdAuthInfo;
import com.joymove.service.JOYIdAuthInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("JOYIdAuthInfoService")
public class JOYIdAuthInfoServiceImpl implements JOYIdAuthInfoService{
	@Autowired
	private JOYIdAuthInfoDao joyIdAuthInfoDao;
	

	

	/****  public proc  **********/
	
	@Override
	public void insertIdAuthInfo(JOYIdAuthInfo joy) {
		// TODO Auto-generated method stub
		joyIdAuthInfoDao.insertIdAuthInfo(joy);
	}

	@Override
	public List<JOYIdAuthInfo> getNeededIdAuthInfo(Map<String, Object> likeCondition) {
		// TODO Auto-generated method stub
		return joyIdAuthInfoDao.getNeededIdAuthInfo(likeCondition);
	}

	

	@Override
	public void updateIdAuthInfo(JOYIdAuthInfo authInfo) {
		// TODO Auto-generated method stub
		joyIdAuthInfoDao.updateIdAuthInfo(authInfo);
		
	}
	
	
	
	

}
