package com.joymove.service.impl;
import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYCar;
import com.joymove.entity.JOYCoupon;
import com.joymove.service.*;
import com.joymove.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
@Service("JOYCarService")
public class JOYCarServiceImpl extends JOYBaseServiceImpl<JOYCar> implements JOYCarService  {


    final static Logger logger = LoggerFactory.getLogger(JOYCarServiceImpl.class);

    @Autowired
    private JOYCarDao joyCarDao;


    public JOYBaseDao getBaseDao() {
        return joyCarDao;
    }

    public Class<JOYCar> getEntityClass() {
        return JOYCar.class;
    }

    public List<JOYCar>  getCarByScope(Map<String, Object> likeCondition){
        return joyCarDao.getCarByScope(likeCondition);
    }
    public void setCarReserve(JOYCar car){
        joyCarDao.setCarReserve(car);
    }
    public void setCarBusy(JOYCar car){
        joyCarDao.setCarBusy(car);

    }
    public void setCarFree(JOYCar car){
        joyCarDao.setCarFree(car);
    }

}
