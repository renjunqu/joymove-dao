package com.joymove.service.impl;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joymove.dao.*;
import com.joymove.entity.*;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joymove.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("JOYOrderService")
public class JOYOrderServiceImpl  extends JOYBaseServiceImpl<JOYOrder> implements JOYOrderService{
    final static Logger logger = LoggerFactory.getLogger(JOYOrderServiceImpl.class);
    @Autowired
    private JOYOrderDao   joyOrderDao;


   public JOYBaseDao getBaseDao() {
        return joyOrderDao;
    }

    public Class<JOYOrder> getEntityClass() {
        return JOYOrder.class;
    }

    public List<JOYOrder> getNeededOrder(Map<String,Object> likeCondition){
        return joyOrderDao.getNeededOrder(likeCondition);
    }

}
