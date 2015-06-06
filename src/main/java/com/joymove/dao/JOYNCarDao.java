package com.joymove.dao;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYNCar;
import com.joymove.entity.JOYOrder;


public interface JOYNCarDao {

	 void insertCar(JOYNCar car);

	 void updateCarRegisterState(JOYNCar car);

	 void updateCarLockState(JOYNCar car);

	 List<JOYNCar> getNeededCar(Map<String, Object> likecondition);

	 List<JOYNCar> getPagedNCarList(Map<String,Object> likeConditon);

	void deleteNCar(JOYNCar car);

	void updateCarInfo(JOYNCar car);
}
