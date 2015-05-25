package com.joymove.service.impl;

import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYDriverLicenseDao;
import com.joymove.entity.JOYDriverLicense;
import com.joymove.service.JOYDriverLicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("JOYDriverLicenseService")
public class JOYDriverLicenseServiceImpl implements JOYDriverLicenseService{
	@Autowired
	private JOYDriverLicenseDao  joydriverlicenseDao;
	
	@Override
	public void insertDriverAuthInfo(JOYDriverLicense joy) {
		// TODO Auto-generated method stub
		joydriverlicenseDao.insertDriverAuthInfo(joy);
	}

	@Override
	public List<JOYDriverLicense> getDriverAuthInfo(
			Map<String, Object> likeCondition) {
		// TODO Auto-generated method stub
		return joydriverlicenseDao.getDriverAuthInfo(likeCondition);
	}

	@Override
	public void updateJOYDriverLicense(JOYDriverLicense joy) {
		// TODO Auto-generated method stub
		joydriverlicenseDao.updateJOYDriverLicense(joy);
		
	}

	
	
	
}
