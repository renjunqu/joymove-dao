package com.joymove.service;

import java.util.List;
import java.util.Map;

import com.joymove.entity.*;

public interface JOYCarService extends  JOYBaseService<JOYCar>  {

    public List<JOYCar>  getCarByScope(Map<String, Object> likeCondition);
    public void setCarReserve(JOYCar car);
    public void setCarBusy(JOYCar car);
    public void setCarFree(JOYCar car);
}
