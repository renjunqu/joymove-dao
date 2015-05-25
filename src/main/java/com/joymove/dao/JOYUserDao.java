package com.joymove.dao;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYUser;




public interface JOYUserDao {
	public void insertJOYUser(JOYUser user);
	
	public List<JOYUser> getJOYUserByPhone(Map<String,Object> likeCondition);
	
	public List<JOYUser> getAllJOYUser(Map<String,Object> likeCondition);
	
	public void updateJOYUser(JOYUser user);
	
	
}


