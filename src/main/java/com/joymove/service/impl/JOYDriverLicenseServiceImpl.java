package com.joymove.service.impl;

import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYDriverLicenseDao;
import com.joymove.dao.JOYUserDao;
import com.joymove.entity.JOYDriverLicense;
import com.joymove.entity.JOYUser;
import com.joymove.service.JOYDriverLicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("JOYDriverLicenseService")
public class JOYDriverLicenseServiceImpl implements JOYDriverLicenseService{
	@Autowired
	private JOYDriverLicenseDao  joydriverlicenseDao;

	@Autowired
	private JOYUserDao joyUserDao;


	public void insertDriverAuthInfo(JOYDriverLicense authInfo) {
		// TODO Auto-generated method stub
		JOYUser user = new JOYUser();
		user.mobileNo = authInfo.mobileNo;
		user.authenticateDriver  = JOYUser.auth_state_ing;
		joydriverlicenseDao.insertDriverAuthInfo(authInfo);
		joyUserDao.updateJOYUser(user);
	}

	public List<JOYDriverLicense> getDriverAuthInfo(
			Map<String, Object> likeCondition) {
		// TODO Auto-generated method stub
		return joydriverlicenseDao.getDriverAuthInfo(likeCondition);
	}

	public void updateJOYDriverLicense(JOYDriverLicense authInfo) {
		// TODO Auto-generated method stub
		JOYUser user = new JOYUser();
		user.mobileNo = authInfo.mobileNo;
		user.authenticateDriver  = JOYUser.auth_state_ing;
		joydriverlicenseDao.updateJOYDriverLicense(authInfo);
		joyUserDao.updateJOYUser(user);
		
	}

	
	
	
}
