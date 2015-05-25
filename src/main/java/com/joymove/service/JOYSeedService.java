package com.joymove.service;

import com.joymove.entity.JOYSeed;

import java.util.Map;

/**
 * Created by figoxu on 15/5/5.
 */
public interface JOYSeedService {
    public void exchangeCoupon(JOYSeed seed);
    public JOYSeed getNeededSeed(Map<String,Object> likeCondition);
}
