package com.joymove.service;

import com.joymove.dao.JOYSeedDao;
import com.joymove.entity.JOYSeed;
import com.joymove.entity.JOYUser;

import java.util.List;
import java.util.Map;

/**
 * Created by figoxu on 15/5/5.
 */
public interface JOYSeedService extends  JOYBaseService<JOYSeed>   {

    public void exchangeCoupon(JOYSeed seed);
}
