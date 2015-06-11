package com.joymove.service.impl;

import com.joymove.dao.JOYBaseDao;
import com.joymove.dao.JOYFansDao;
import com.joymove.dao.JOYIdAuthInfoDao;
import com.joymove.entity.JOYFans;
import com.joymove.entity.JOYIdAuthInfo;
import com.joymove.service.JOYFansService;
import com.joymove.service.JOYIdAuthInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by qurj on 15/5/26.
 */
@Service("JOYFansService")
public class JOYFansServiceImpl extends JOYBaseServiceImpl<JOYFans> implements JOYFansService {

    final static Logger logger = LoggerFactory.getLogger(JOYFansServiceImpl.class);

    @Autowired
    private JOYFansDao joyFansDao;



    public JOYBaseDao getBaseDao() {
        return joyFansDao;
    }

    public Class<JOYFans> getEntityClass() {
        return JOYFans.class;
    }
}
