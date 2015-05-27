package com.joymove.dao;

import com.joymove.entity.JOYUser;

import java.util.List;
import java.util.Map;
import com.joymove.entity.JOYWXPayInfo;

/**
 * Created by qurj on 15/5/12.
 */
public interface JOYWXPayInfoDao {

     void insertWXPayInfo(JOYWXPayInfo  payInfo);
     List<JOYWXPayInfo> getNeededPayInfo(Map<String,Object> likeCondition);
     void markPayInfo(JOYWXPayInfo payInfo);
}
