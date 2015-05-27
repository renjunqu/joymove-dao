package com.joymove.dao;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYUser;




public interface JOYUserDao {
	 void insertJOYUser(JOYUser user);

	 List<JOYUser> getNeededUser(JOYUser user);
	
	 void updateJOYUser(JOYUser user);
	
}


