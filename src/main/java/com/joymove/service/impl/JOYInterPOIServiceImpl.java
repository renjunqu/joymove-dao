package com.joymove.service.impl;

import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYInterPOIDao;
import com.joymove.entity.JOYInterPOI;
import com.joymove.service.JOYInterPOIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("JOYInterPOIService")
public class JOYInterPOIServiceImpl  implements JOYInterPOIService {
	@Autowired
	public JOYInterPOIDao joyInterPOIDao;
	


	/********* business proc    **************/
	
	@Override
	public List<JOYInterPOI> getAllPOI() {
		return joyInterPOIDao.getAllPOI();
	}
    
}
