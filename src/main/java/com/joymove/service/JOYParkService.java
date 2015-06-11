package com.joymove.service;

import java.util.List;
import java.util.Map;

import com.joymove.dao.*;
import com.joymove.entity.JOYPark;
import com.joymove.entity.JOYUser;

public interface JOYParkService  extends  JOYBaseService<JOYPark>  {

    public List<JOYPark>  getParkByScope(Map<String, Object> likeCondition);


}
