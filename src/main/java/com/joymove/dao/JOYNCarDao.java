package com.joymove.dao;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYNCar;


public interface JOYNCarDao {

	public void insertCar(JOYNCar car);

	public void updateCarRegisterState(JOYNCar car);

	public List<JOYNCar> getNeededCar(Map<String, Object> condition);
}
