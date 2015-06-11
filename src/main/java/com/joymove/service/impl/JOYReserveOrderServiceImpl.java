package com.joymove.service.impl;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymove.service.*;
import com.joymove.dao.*;
import com.joymove.entity.*;

import javax.annotation.Resource;

//import org.springframework.scheduling


@Service("JOYReserveOrderService")
public class JOYReserveOrderServiceImpl  extends JOYBaseServiceImpl<JOYReserveOrder> implements JOYReserveOrderService {
    final static Logger logger = LoggerFactory.getLogger(JOYReserveOrderServiceImpl.class);
    @Autowired
    private JOYReserveOrderDao joyReserveOrderDao;


    public JOYBaseDao getBaseDao() {
        return joyReserveOrderDao;
    }

    public Class<JOYReserveOrder> getEntityClass() {
        return JOYReserveOrder.class;
    }
}
