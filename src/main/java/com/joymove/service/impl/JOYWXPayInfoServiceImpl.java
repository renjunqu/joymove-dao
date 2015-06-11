package com.joymove.service.impl;

import com.joymove.dao.JOYBaseDao;
import com.joymove.dao.JOYUserDao;
import com.joymove.dao.JOYWXPayInfoDao;
import com.joymove.entity.JOYUser;
import com.joymove.entity.JOYWXPayInfo;
import com.joymove.service.JOYUserService;
import com.joymove.service.JOYWXPayInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by qurj on 15/5/12.
 */

@Service("JOYWXPayInfoService")
public class JOYWXPayInfoServiceImpl extends JOYBaseServiceImpl<JOYWXPayInfo> implements JOYWXPayInfoService {

    final static Logger logger = LoggerFactory.getLogger(JOYWXPayInfoServiceImpl.class);
    @Autowired
    private JOYWXPayInfoDao joywxPayInfoDao;


    public JOYBaseDao getBaseDao(){
        return joywxPayInfoDao;
    }

    public Class<JOYWXPayInfo> getEntityClass(){
        return JOYWXPayInfo.class;
    }

}
