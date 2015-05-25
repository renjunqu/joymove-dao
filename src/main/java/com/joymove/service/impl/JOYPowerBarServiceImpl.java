package com.joymove.service.impl;
import com.joymove.service.*;
import com.joymove.dao.*;
import com.joymove.entity.JOYPowerBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.math.*;


@Service("JOYPowerBarService")
public class JOYPowerBarServiceImpl implements JOYPowerBarService {
	@Autowired
	JOYPowerBarDao joyPowerBarDao;


	public List<JOYPowerBar>  getPowerBarByScope(Map<String, Object> likeCondition){
		return joyPowerBarDao.getPowerBarByScope(likeCondition);
	}

}
