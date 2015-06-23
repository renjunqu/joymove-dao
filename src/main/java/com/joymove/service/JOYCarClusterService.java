package com.joymove.service;

import com.joymove.entity.JOYCar;
import com.joymove.entity.JOYCarCluster;

import java.util.List;
import java.util.Map;

/**
 * Created by qurj on 15/6/23.
 */
public interface  JOYCarClusterService extends  JOYBaseService<JOYCarCluster> {
    List<JOYCarCluster> getCarClusterByScope(Map<String, Object> likeCondition);

}
