package com.joymove.service;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYNCar;

public interface JOYNCarService {

	void insertCar(JOYNCar car);

	void updateCarRegisterState(JOYNCar car);

	List<JOYNCar> getNeededCar(Map<String, Object> condition);

}
