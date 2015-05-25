package com.joymove.service.impl;

import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYIntegrationDao;
import com.joymove.dao.JOYUserDao;
import com.joymove.entity.JOYIntegration;
import com.joymove.service.JOYIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("JOYIntegrationService")
public class JOYIntegrationServiceImpl implements JOYIntegrationService{

	@Autowired
	private JOYIntegrationDao   joyintegrationDao;

	@Override
	public List<JOYIntegration> getJOYIntegration(
			Map<String, Object> likeCondition) {
		// TODO Auto-generated method stub
		
		return joyintegrationDao.getJOYIntegration(likeCondition);
	}

	@Override
	public void insertJOYIntegration(JOYIntegration joy) {
		joyintegrationDao.insertJOYIntegration(joy);
		
	}
	
	

}
