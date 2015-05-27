package com.joymove.dao;

import java.util.List;
import java.util.Map;
import com.joymove.entity.*;



public interface JOYCarDao {
	 List<JOYCar>  getCarByScope(Map<String, Object> likeCondition);
	
	 void setCarReserve(JOYCar car);
	 void setCarBusy(JOYCar car);
	 void setCarFree(JOYCar car);
	 List<JOYCar>  getCarById(Map<String, Object> likeCondition);
	 void clearReserve(JOYCar car);
}
