package com.joymove.service.impl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYBaseDao;
import com.joymove.dao.JOYNCarDao;
import com.joymove.entity.JOYNCar;
import com.joymove.entity.JOYReserveOrder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.futuremove.cacheServer.entity.Car;
import com.futuremove.cacheServer.service.CarService;
import com.futuremove.cacheServer.utils.ConfigUtils;
import com.futuremove.cacheServer.utils.HttpPostUtils;
import com.joymove.dao.JOYOrderDao;
import com.joymove.dao.JOYReserveOrderDao;
import com.joymove.entity.JOYOrder;
import com.joymove.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("JOYNOrderService")
public class JOYNOrderServiceImpl extends JOYBaseServiceImpl<JOYOrder> implements JOYNOrderService {

    final static Logger logger = LoggerFactory.getLogger(JOYNOrderServiceImpl.class);

    @Autowired
    private JOYOrderDao   joyOrderDao;

    public JOYBaseDao getBaseDao() {
        return joyOrderDao;
    }

    public Class<JOYOrder> getEntityClass() {
        return JOYOrder.class;
    }
}
