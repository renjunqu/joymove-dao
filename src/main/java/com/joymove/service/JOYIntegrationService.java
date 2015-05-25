package com.joymove.service;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYIntegration;

public interface JOYIntegrationService {
	
    public List<JOYIntegration> getJOYIntegration(Map<String, Object> likeCondition);
	
	public void insertJOYIntegration(JOYIntegration joy);
}
