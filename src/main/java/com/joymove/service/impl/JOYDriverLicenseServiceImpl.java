package com.joymove.service.impl;

import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYBaseDao;
import com.joymove.dao.JOYDriverLicenseDao;
import com.joymove.dao.JOYDynamicPwsDao;
import com.joymove.dao.JOYUserDao;
import com.joymove.entity.JOYDriverLicense;
import com.joymove.entity.JOYDynamicPws;
import com.joymove.entity.JOYUser;
import com.joymove.service.JOYDriverLicenseService;
import com.joymove.service.JOYDynamicPwsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("JOYDriverLicenseService")
public class JOYDriverLicenseServiceImpl extends JOYBaseServiceImpl<JOYDriverLicense> implements JOYDriverLicenseService   {

    final static Logger logger = LoggerFactory.getLogger(JOYDriverLicenseServiceImpl.class);

    @Autowired
    private JOYDriverLicenseDao joyDriverLicenseDao;

    public JOYBaseDao getBaseDao() {
        return joyDriverLicenseDao;
    }

    public Class<JOYDriverLicense> getEntityClass() {
        return JOYDriverLicense.class;
    }
}
