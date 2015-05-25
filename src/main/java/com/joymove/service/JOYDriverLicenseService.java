package com.joymove.service;

import java.util.List;
import java.util.Map;


import com.joymove.entity.JOYDriverLicense;

public interface JOYDriverLicenseService {
		
	public void insertDriverAuthInfo(JOYDriverLicense joy);
	
	public List<JOYDriverLicense> getDriverAuthInfo(Map<String,Object> likeCondition);
	
	public void updateJOYDriverLicense(JOYDriverLicense joy);
}
