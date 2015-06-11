package com.joymove.dao;

import java.util.List;
import java.util.Map;
import com.joymove.entity.*;



public interface JOYCarDao extends JOYBaseDao<JOYCar>  {
    void clearReserve(JOYCar car);
    List<JOYCar>  getCarByScope(Map<String, Object> likeCondition);
    void setCarReserve(JOYCar car);
    void setCarBusy(JOYCar car);
    void setCarFree(JOYCar car);

}
