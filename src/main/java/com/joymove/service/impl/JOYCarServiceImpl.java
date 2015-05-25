package com.joymove.service.impl;
import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYCar;
import com.joymove.service.*;
import com.joymove.dao.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
@Service("JOYCarService")
public class JOYCarServiceImpl implements JOYCarService {
	@Autowired
	private JOYCarDao joyCarDao;


	public List<JOYCar>  getCarByScope(Map<String, Object> likeCondition){
		return joyCarDao.getCarByScope(likeCondition);	
	}
	public void setCarReserve(JOYCar car){
		 joyCarDao.setCarReserve(car);
	}
	public void setCarBusy(JOYCar car){
		joyCarDao.setCarBusy(car);
		
	}
	public void setCarFree(JOYCar car){
		joyCarDao.setCarFree(car);
	}
	public List<JOYCar>  getCarById(Map<String, Object> likeCondition){
		return joyCarDao.getCarById(likeCondition);
	}
}
