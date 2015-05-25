package com.joymove.dao;

import java.util.List;
import java.util.Map;
import com.joymove.entity.JOYDriverLicense;


public interface JOYDriverLicenseDao {

	public void insertDriverAuthInfo(JOYDriverLicense joy);
	
	public List<JOYDriverLicense> getDriverAuthInfo(Map<String,Object> likeCondition);
	
	
	public void updateJOYDriverLicense(JOYDriverLicense joy);
}
