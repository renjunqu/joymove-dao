package com.joymove.service.impl;
import com.joymove.entity.JOYReserveOrder;
import com.joymove.service.*;
import com.joymove.dao.*;
import com.joymove.entity.JOYPowerBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.math.*;


@Service("JOYPowerBarService")
public class JOYPowerBarServiceImpl  extends JOYBaseServiceImpl<JOYPowerBar> implements JOYPowerBarService {

    final static Logger logger = LoggerFactory.getLogger(JOYPowerBarServiceImpl.class);
    @Autowired
    private JOYPowerBarDao  joyPowerBarDao;


    public JOYBaseDao getBaseDao() {
        return joyPowerBarDao;
    }

    public Class<JOYPowerBar> getEntityClass() {
        return JOYPowerBar.class;
    }

    public List<JOYPowerBar>  getPowerBarByScope(Map<String, Object> likeCondition){
        return joyPowerBarDao.getPowerBarByScope(likeCondition);
    }

}
