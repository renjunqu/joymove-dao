package com.joymove.service;

import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;

import com.joymove.entity.JOYReserveOrder;

public interface JOYReserveOrderService {
	
	List<JOYReserveOrder> getNeededReserveOrder(Map<String,Object> likeCondition);
	boolean insertReserveOrder(JOYReserveOrder cOrder) throws SchedulerException;
	void updateReserveOrderDelFlag(JOYReserveOrder cOrder) throws SchedulerException;
}
