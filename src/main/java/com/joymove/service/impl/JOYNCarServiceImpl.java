package com.joymove.service.impl;
import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYBaseDao;
import com.joymove.dao.JOYNCarDao;
import com.joymove.dao.JOYOrderDao;
import com.joymove.entity.JOYNCar;
import com.joymove.entity.JOYOrder;
import com.joymove.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("JOYNCarService")
public class JOYNCarServiceImpl extends JOYBaseServiceImpl<JOYNCar> implements JOYNCarService  {
    final static Logger logger = LoggerFactory.getLogger(JOYNCarServiceImpl.class);

    @Autowired
    private JOYNCarDao  joynCarDao;

    public JOYBaseDao getBaseDao() {
        return joynCarDao;
    }

    public Class<JOYNCar> getEntityClass() {
        return JOYNCar.class;
    }
}
