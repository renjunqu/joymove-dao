package com.joymove.service.impl;

import com.joymove.dao.JOYBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joymove.dao.JOYReserveOrderDao;
import com.joymove.entity.JOYReserveOrder;
import com.joymove.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
