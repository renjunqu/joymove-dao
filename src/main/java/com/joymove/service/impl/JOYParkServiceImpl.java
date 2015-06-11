package com.joymove.service.impl;
import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYPark;
import com.joymove.entity.JOYPowerBar;
import com.joymove.service.*;
import com.joymove.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("JOYParkService")
public class JOYParkServiceImpl extends JOYBaseServiceImpl<JOYPark> implements JOYParkService {

    final static Logger logger = LoggerFactory.getLogger(JOYParkServiceImpl.class);
    @Autowired
    private JOYParkDao  joyParkDao;


    public JOYBaseDao getBaseDao() {
        return joyParkDao;
    }

    public Class<JOYPark> getEntityClass() {
        return JOYPark.class;
    }
    @Override
    public List<JOYPark> getParkByScope(Map<String, Object> likeCondition) {

        return joyParkDao.getParkByScope(likeCondition);
    }
}
