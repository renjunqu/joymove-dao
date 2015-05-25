package com.joymove.service;

import java.util.List;
import java.util.Map;

import com.joymove.dao.*;
import com.joymove.entity.JOYPark;

public interface JOYParkService {
	
	public List<JOYPark>  getParkByScope(Map<String, Object> likeCondition);
	

}
