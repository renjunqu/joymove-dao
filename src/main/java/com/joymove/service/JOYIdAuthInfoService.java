package com.joymove.service;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYIdAuthInfo;

public interface JOYIdAuthInfoService {
	
	public void insertIdAuthInfo(JOYIdAuthInfo joy);
	
	public List<JOYIdAuthInfo> getNeededIdAuthInfo(Map<String,Object> likeCondition);

	public void updateIdAuthInfo(JOYIdAuthInfo authInfo);
}
