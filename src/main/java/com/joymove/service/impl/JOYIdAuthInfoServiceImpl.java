package com.joymove.service.impl;

import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYBaseDao;
import com.joymove.dao.JOYIdAuthInfoDao;
import com.joymove.dao.JOYIntegrationDao;
import com.joymove.dao.JOYUserDao;
import com.joymove.entity.JOYIdAuthInfo;
import com.joymove.entity.JOYIntegration;
import com.joymove.entity.JOYUser;
import com.joymove.service.JOYIdAuthInfoService;
import com.joymove.service.JOYIntegrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("JOYIdAuthInfoService")
public class JOYIdAuthInfoServiceImpl extends JOYBaseServiceImpl<JOYIdAuthInfo> implements JOYIdAuthInfoService  {

    final static Logger logger = LoggerFactory.getLogger(JOYIdAuthInfoServiceImpl.class);

    @Autowired
    private JOYIdAuthInfoDao joyIdAuthInfoDao;

    public JOYBaseDao getBaseDao() {
        return joyIdAuthInfoDao;
    }

    public Class<JOYIdAuthInfo> getEntityClass() {
        return JOYIdAuthInfo.class;
    }
}
