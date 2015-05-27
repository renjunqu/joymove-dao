package com.joymove.dao;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYReserveOrder;



public interface JOYReserveOrderDao {
	
	
	 void insertReserveOrder(JOYReserveOrder cOrder);
	
	 List<JOYReserveOrder> getNeededReserveOrder(
			Map<String, Object> likeCondition);
	 void updateReserveOrderDelFlag(JOYReserveOrder cOrder);

	 void insertNReserveOrder(JOYReserveOrder cOrder);

}
