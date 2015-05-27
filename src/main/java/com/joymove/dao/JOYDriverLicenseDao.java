package com.joymove.dao;

import java.util.List;
import java.util.Map;
import com.joymove.entity.JOYDriverLicense;


public interface JOYDriverLicenseDao {

	 void insertDriverAuthInfo(JOYDriverLicense driverLicense);
	
	 List<JOYDriverLicense> getDriverAuthInfo(Map<String,Object> likeCondition);
	
	
	 void updateJOYDriverLicense(JOYDriverLicense driverLicense);
}
