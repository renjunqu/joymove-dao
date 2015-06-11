package com.joymove.service.impl;



import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYBaseDao;
import com.joymove.dao.JOYDynamicPwsDao;
import com.joymove.dao.JOYFansDao;
import com.joymove.entity.JOYDynamicPws;
import com.joymove.entity.JOYFans;
import com.joymove.service.JOYDynamicPwsService;
import com.joymove.service.JOYFansService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("JOYDynamicPwsService")
public class JOYDynamicPwsServiceImpl extends JOYBaseServiceImpl<JOYDynamicPws> implements JOYDynamicPwsService  {

    final static Logger logger = LoggerFactory.getLogger(JOYDynamicPwsServiceImpl.class);

    @Autowired
    private JOYDynamicPwsDao  joyDynamicPwsDao;

    public JOYBaseDao getBaseDao() {
        return joyDynamicPwsDao;
    }

    public Class<JOYDynamicPws> getEntityClass() {
        return JOYDynamicPws.class;
    }
}
