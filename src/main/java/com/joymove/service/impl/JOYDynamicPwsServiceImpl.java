package com.joymove.service.impl;



import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYDynamicPwsDao;
import com.joymove.entity.JOYDynamicPws;
import com.joymove.service.JOYDynamicPwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("JOYDynamicPwsService")
public class JOYDynamicPwsServiceImpl implements JOYDynamicPwsService{
	@Autowired
	private  JOYDynamicPwsDao  joydynamicpwsDao;


	public void insertDynamicPwse(JOYDynamicPws dynamicpws) {
		// TODO Auto-generated method stub
		joydynamicpwsDao.insertDynamicPws(dynamicpws);
	}

	


	public List<JOYDynamicPws> getDynamicPws(Map<String, Object> likeCondition) {
		
		List<JOYDynamicPws> dynamicPws = joydynamicpwsDao.getDynamicPws(likeCondition);
		return dynamicPws;
	}

	
	
	

	


	
}
