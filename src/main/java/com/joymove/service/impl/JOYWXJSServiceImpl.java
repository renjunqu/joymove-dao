package com.joymove.service.impl;

import com.futuremove.cacheServer.utils.HttpGetUtils;
import com.joymove.dao.JOYBaseDao;
import com.joymove.dao.JOYWXJSDataDao;
import com.joymove.dao.JOYWXPayInfoDao;
import com.joymove.entity.JOYWXJSData;
import com.joymove.entity.JOYWXPayInfo;
import com.joymove.service.JOYWXJSService;
import com.joymove.service.JOYWXPayInfoService;
import com.mongodb.util.JSON;
import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * Created by qurj on 15/5/28.
 */
@Service("JOYWXJSService")
public class JOYWXJSServiceImpl extends JOYBaseServiceImpl<JOYWXJSData> implements JOYWXJSService {

    final static Logger logger = LoggerFactory.getLogger(JOYWXJSServiceImpl.class);
    @Autowired
    private JOYWXJSDataDao joywxjsDataDao;


    public JOYBaseDao getBaseDao() {
        return joywxjsDataDao;
    }

    public Class<JOYWXJSData> getEntityClass() {
        return JOYWXJSData.class;
    }
}
