package com.joymove.service;

import org.quartz.SchedulerException;

import com.futuremove.cacheServer.entity.Car;

public interface JOYNReserveOrderService {

	boolean  insertReserveOrder(Car cacheCar) throws  Exception;

	void updateReserveOrderDelFlag(String mobileNo) throws SchedulerException;

}
