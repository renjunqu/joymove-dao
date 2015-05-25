package com.joymove.service.impl;
import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYNCarDao;
import com.joymove.entity.JOYNCar;
import com.joymove.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("JOYNCarService")
public class JOYNCarServiceImpl implements JOYNCarService  {
	@Autowired
	private JOYNCarDao joyNCarDao;
	
	
	
	




	public JOYNCarServiceImpl(JOYNCarDao joyNCarDao) {
		super();
		this.joyNCarDao = joyNCarDao;
	}
	
	




	public JOYNCarServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}


	/****   business  proce ****/


	public void insertCar(JOYNCar car){
		   joyNCarDao.insertCar(car);
	}




	@Override
	public void updateCarRegisterState(JOYNCar car) {
		joyNCarDao.updateCarRegisterState(car);
	}




	@Override
	public List<JOYNCar> getNeededCar(Map<String, Object> condition) {
		return joyNCarDao.getNeededCar(condition);
	}

}
