package com.joymove.service.impl;

import com.joymove.dao.JOYBaseDao;
import com.joymove.dao.JOYPayHistoryDao;
import com.joymove.dao.JOYPowerBarDao;
import com.joymove.entity.JOYPayHistory;
import com.joymove.entity.JOYPowerBar;
import com.joymove.service.JOYPayHistoryService;
import com.joymove.service.JOYPowerBarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jessie on 2015/6/13.
 */

@Service("JOYPayHistoryService")
public class JOYPayHistoryServiceImpl  extends JOYBaseServiceImpl<JOYPayHistory> implements JOYPayHistoryService {
    final static Logger logger = LoggerFactory.getLogger(JOYPayHistoryServiceImpl.class);
    @Autowired
    private JOYPayHistoryDao joyPayHistoryDao;


    public JOYBaseDao getBaseDao() {
        return this.joyPayHistoryDao;
    }

    public Class<JOYPayHistory> getEntityClass() {
        return JOYPayHistory.class;
    }


}
