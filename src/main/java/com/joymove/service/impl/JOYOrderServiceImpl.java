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

    public List<JOYOrder> getNeededOrder(Map<String, Object> likeCondition) {
        // TODO Auto-generated method stub
        return joyOrderDao.getNeededOrder(likeCondition);
    }

    public static void main(String [] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:test.xml");
        JOYOrderService  service = (JOYOrderService)context.getBean("JOYOrderService");
        JOYOrder orderFilter = new JOYOrder();
        orderFilter.mobileNo = "18518019074";
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("minStartTime", new Date(System.currentTimeMillis()-(24L*3600L*1L*1000L)));

    }

}
