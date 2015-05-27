package com.joymove.service;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYUser;



public interface JOYUserService {
	public JOYUser insertJOYUser (JOYUser user);
	
	public void updateJOYUser(JOYUser user);
	
	public int triggerUserCache(JOYUser user);
	
	
	public List<JOYUser> getNeededUser(JOYUser user);


	String checkUserState(JOYUser user);
}
