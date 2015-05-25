package com.joymove.service;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYUser;



public interface JOYUserService {
	public JOYUser insertJOYUser (JOYUser user);
	
	
	/*
	public List<JOYUser> getJOYUserByPhone(Map<String,Object> likeCondition);
	
	
	public List<JOYUser> getJOYUserInfo(Map<String, Object> likeCondition);
	*/
	
	public void updateJOYUser(JOYUser user);
	
	public int triggerUserCache(JOYUser user);
	
	
	public List<JOYUser> getJOyUserNeeded(Map<String, Object> likeCondition);


	List<JOYUser> getJOYUserByMobileNo(JOYUser user);


	List<JOYUser> getJOYUserInfo(JOYUser user);


	String checkUserState(JOYUser user);
}
