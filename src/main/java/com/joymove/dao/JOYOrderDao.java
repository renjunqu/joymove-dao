package com.joymove.dao;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYOrder;


public interface JOYOrderDao {
	void insertOrder(JOYOrder order);

	void deleteOrder(JOYOrder order);

	List<JOYOrder> getNeededOrder(Map<String, Object> likeCondition);

	void updateDestination(JOYOrder cOrder);

	void changeBatonMode(JOYOrder cOrder);

	void updateOrderStop(JOYOrder order);

	JOYOrder getJOYOrderById(Map<String, Object> likeCondition);

	void insertNOrder(JOYOrder order);

	void changeNBatonMode(JOYOrder cOrder);

	void updateNDestination(JOYOrder cOrder);

	void updateNOrderStop(JOYOrder cOrder);

	List<JOYOrder> getPagedOrderList(Map<String,Object> likeConditon);
}
