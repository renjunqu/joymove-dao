package com.joymove.service.impl;

import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYBaseDao;
import com.joymove.dao.JOYMessageDao;
import com.joymove.dao.JOYNCarDao;
import com.joymove.entity.JOYMessage;
import com.joymove.entity.JOYNCar;
import com.joymove.service.JOYMessageService;
import com.joymove.service.JOYNCarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("JOYMessageService")
public class JOYMessageServiceImpl extends JOYBaseServiceImpl<JOYMessage> implements JOYMessageService  {
    final static Logger logger = LoggerFactory.getLogger(JOYMessageServiceImpl.class);

    @Autowired
    private JOYMessageDao  joyMessageDao;

    public JOYBaseDao getBaseDao() {
        return joyMessageDao;
    }

    public Class<JOYMessage> getEntityClass() {
        return JOYMessage.class;
    }
}
