package com.joymove.dao;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYOrder;


public interface JOYOrderDao extends JOYBaseDao<JOYOrder>  {

    List<JOYOrder> getNeededOrder(Map<String, Object> likeCondition);

}
