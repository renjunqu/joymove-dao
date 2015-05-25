package com.joymove.dao;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYIdAuthInfo;

public interface JOYIdAuthInfoDao {
	
	public void insertIdAuthInfo(JOYIdAuthInfo authInfo);
	
	public List<JOYIdAuthInfo> getNeededIdAuthInfo(Map<String,Object> likeCondition);

	public void updateIdAuthInfo(JOYIdAuthInfo authInfo);
	
	
}
