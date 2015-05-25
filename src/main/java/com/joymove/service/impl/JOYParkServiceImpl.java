package com.joymove.service.impl;
import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYPark;
import com.joymove.service.*;
import com.joymove.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("JOYParkService")
public class JOYParkServiceImpl implements JOYParkService {
	@Autowired
	JOYParkDao joyParkDao;



	@Override
	public List<JOYPark> getParkByScope(Map<String, Object> likeCondition) {
		
		return joyParkDao.getParkByScope(likeCondition);
	}
	
	

}
