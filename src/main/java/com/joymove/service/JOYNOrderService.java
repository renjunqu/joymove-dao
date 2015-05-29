package com.joymove.service;

import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;

import com.futuremove.cacheServer.entity.Car;
import com.joymove.entity.JOYOrder;

public interface JOYNOrderService {

	List<JOYOrder> getNeededOrder(Map<String, Object> likeCondition);

	boolean  updateOrderCancel(Car car) throws  Exception;

	void updateOrderTermiate(Car car) throws  Exception;

	void insertNOrder(JOYOrder order) throws SchedulerException;

	void changeNBatonMode(JOYOrder cOrder);

	void updateNDestination(JOYOrder cOrder);

}
