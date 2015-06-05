package com.joymove.service;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYOrder;

public interface JOYOrderService {

	public boolean insertOrder(JOYOrder order) throws Exception;

	public void deleteOrder(JOYOrder order);

	public List<JOYOrder> getNeededOrder(Map<String, Object> likeCondition);

	public void updateDestination(JOYOrder cOrder);

	public void changeBatonMode(JOYOrder cOrder);

	public void updateOrderStop(JOYOrder order);

	public void deleteOrder(Long[] orderIds, JOYOrder order);

	public JOYOrder getOrderById(Map<String, Object> likeCondition);

	List<JOYOrder> getPagedOrderList(Map<String,Object> likeConditon);

}
