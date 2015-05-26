package com.joymove.service.impl;

import com.joymove.dao.JOYFansDao;
import com.joymove.entity.JOYFans;
import com.joymove.service.JOYFansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by qurj on 15/5/26.
 */
@Service("JOYFansService")
public class JOYFansServiceImpl implements JOYFansService  {
    @Autowired
    private JOYFansDao joyFansDao;

    public void insertFans(JOYFans fans){
           joyFansDao.addNewFans(fans);
    }
}
