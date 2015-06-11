package com.joymove.service.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYBaseDao;
import com.joymove.dao.JOYNCarDao;
import com.joymove.dao.JOYOrderDao;
import com.joymove.entity.JOYNCar;
import com.joymove.entity.JOYOrder;
import org.mongodb.morphia.Datastore;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.futuremove.cacheServer.entity.Car;
import com.futuremove.cacheServer.jobs.ClearExpireReserveJob;
import com.futuremove.cacheServer.service.CarService;
import com.futuremove.cacheServer.service.impl.CarServiceImpl;
import com.futuremove.cacheServer.test.Human;
import com.joymove.dao.JOYReserveOrderDao;
import com.joymove.entity.JOYReserveOrder;
import com.joymove.service.*;
import static org.quartz.JobBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.CalendarIntervalScheduleBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.DateBuilder.*;
import  com.futuremove.cacheServer.jobs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("JOYNReserveOrderService")
public class JOYNReserveOrderServiceImpl extends JOYBaseServiceImpl<JOYReserveOrder> implements JOYNReserveOrderService  {

    final static Logger logger = LoggerFactory.getLogger(JOYNReserveOrderServiceImpl.class);

    @Autowired
    private JOYReserveOrderDao joyReserveOrderDao;

    public JOYBaseDao getBaseDao() {
        return joyReserveOrderDao;
    }

    public Class<JOYReserveOrder> getEntityClass() {
        return JOYReserveOrder.class;
    }
}
