package com.joymove.dao;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYIntegration;


public interface JOYIntegrationDao {
	
	
	 List<JOYIntegration> getJOYIntegration(Map<String, Object> likeCondition);
	
	 void insertJOYIntegration(JOYIntegration integration);
}
