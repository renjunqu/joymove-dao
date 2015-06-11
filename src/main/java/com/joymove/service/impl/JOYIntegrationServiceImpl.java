package com.joymove.service.impl;

import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYBaseDao;
import com.joymove.dao.JOYIntegrationDao;
import com.joymove.dao.JOYInterPOIDao;
import com.joymove.dao.JOYUserDao;
import com.joymove.entity.JOYIntegration;
import com.joymove.entity.JOYInterPOI;
import com.joymove.service.JOYIntegrationService;
import com.joymove.service.JOYInterPOIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("JOYIntegrationService")
public class JOYIntegrationServiceImpl extends JOYBaseServiceImpl<JOYIntegration> implements JOYIntegrationService {

    final static Logger logger = LoggerFactory.getLogger(JOYIntegrationServiceImpl.class);

    @Autowired
    private JOYIntegrationDao  joyIntegrationDao;

    public JOYBaseDao getBaseDao() {
        return joyIntegrationDao;
    }

    public Class<JOYIntegration> getEntityClass() {
        return JOYIntegration.class;
    }
}
