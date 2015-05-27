package com.joymove.dao;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYIdAuthInfo;

public interface JOYIdAuthInfoDao {
	
	 void insertIdAuthInfo(JOYIdAuthInfo authInfo);
	
	 List<JOYIdAuthInfo> getNeededIdAuthInfo(Map<String,Object> likeCondition);

	 void updateIdAuthInfo(JOYIdAuthInfo authInfo);
	
	
}
