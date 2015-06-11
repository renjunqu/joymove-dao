package com.joymove.service;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYOrder;
import com.joymove.entity.JOYUser;

public interface JOYOrderService extends  JOYBaseService<JOYOrder>  {

    public List<JOYOrder> getNeededOrder(Map<String, Object> likeCondition);
}
