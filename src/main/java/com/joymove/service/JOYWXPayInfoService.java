package com.joymove.service;

import com.joymove.entity.JOYWXPayInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by qurj on 15/5/12.
 */
public interface JOYWXPayInfoService {

    public void insertWXPayInfo(JOYWXPayInfo payInfo);
    public List<JOYWXPayInfo> getNeededPayInfo(Map<String,Object> likeCondition);
    public void markPayInfo(JOYWXPayInfo payInfo);

}
