package com.joymove.service.impl;

import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYBaseDao;
import com.joymove.dao.JOYInterPOIDao;
import com.joymove.dao.JOYMessageDao;
import com.joymove.entity.JOYInterPOI;
import com.joymove.entity.JOYMessage;
import com.joymove.service.JOYInterPOIService;
import com.joymove.service.JOYMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("JOYInterPOIService")
public class JOYInterPOIServiceImpl  extends JOYBaseServiceImpl<JOYInterPOI> implements JOYInterPOIService  {

    final static Logger logger = LoggerFactory.getLogger(JOYInterPOIServiceImpl.class);

    @Autowired
    private JOYInterPOIDao joyInterPOIDao;

    public JOYBaseDao getBaseDao() {
        return joyInterPOIDao;
    }

    public Class<JOYInterPOI> getEntityClass() {
        return JOYInterPOI.class;
    }
}
