package com.joymove.dao;

import com.joymove.entity.JOYCar;
import com.joymove.entity.JOYCarCluster;
import com.joymove.entity.JOYPowerBar;

import java.util.List;
import java.util.Map;

/**
 * Created by qurj on 15/6/23.
 */
public interface  JOYCarClusterDao  extends JOYBaseDao<JOYCarCluster> {
    List<JOYCarCluster> getCarClusterByScope(Map<String, Object> likeCondition);
}
