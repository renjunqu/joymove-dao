package com.joymove.service.impl;

import com.joymove.dao.JOYBaseDao;
import com.joymove.dao.JOYPayReqInfoDao;
import com.joymove.dao.JOYUserDao;
import com.joymove.entity.JOYPayReqInfo;
import com.joymove.entity.JOYUser;
import com.joymove.service.JOYPayReqInfoService;
import com.joymove.service.JOYUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by qurj on 15/5/12.
 */

@Service("JOYPayReqInfoService")
public class JOYPayReqInfoServiceImpl extends JOYBaseServiceImpl<JOYPayReqInfo> implements JOYPayReqInfoService {

    final static Logger logger = LoggerFactory.getLogger(JOYPayReqInfoServiceImpl.class);
    @Autowired
    private JOYPayReqInfoDao  joyPayReqInfoDao;


    public JOYBaseDao getBaseDao(){
        return joyPayReqInfoDao;
    }

    public Class<JOYPayReqInfo> getEntityClass(){
        return JOYPayReqInfo.class;
    }

}
