package com.joymove.dao;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYReserveOrder;



public interface JOYReserveOrderDao {
	
	
	public void insertReserveOrder(JOYReserveOrder cOrder);
	
	public List<JOYReserveOrder> getNeededReserveOrder(
			Map<String, Object> likeCondition);
	public void updateReserveOrderDelFlag(JOYReserveOrder cOrder);

	public void insertNReserveOrder(JOYReserveOrder cOrder);

}
