package com.joymove.service;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYNCar;
import com.joymove.entity.JOYOrder;

public interface JOYNCarService {

	void insertCar(JOYNCar car);

	void updateCarRegisterState(JOYNCar car);

	void updateCarLockState(JOYNCar car);

	List<JOYNCar> getNeededCar(Map<String, Object> condition);

	List<JOYNCar> getPagedNCarList(Map<String,Object> likeConditon);

	void deleteNCar(JOYNCar car);

	void updateCarInfo(JOYNCar car);


}
