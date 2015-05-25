package com.joymove.service.impl;

import com.joymove.dao.JOYUserDao;
import com.joymove.dao.JOYWXPayInfoDao;
import com.joymove.entity.JOYWXPayInfo;
import com.joymove.service.JOYWXPayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by qurj on 15/5/12.
 */

@Service("JOYWXPayInfoService")
public class JOYWXPayInfoServiceImpl implements JOYWXPayInfoService{
    @Autowired
    private JOYWXPayInfoDao joywxPayInfoDao;

    public void insertWXPayInfo(JOYWXPayInfo payInfo) {
        joywxPayInfoDao.insertWXPayInfo(payInfo);

    }
    public List<JOYWXPayInfo> getNeededPayInfo(Map<String,Object> likeCondition){
        return joywxPayInfoDao.getNeededPayInfo(likeCondition);
    }

    public void markPayInfo(JOYWXPayInfo payInfo){
        joywxPayInfoDao.markPayInfo(payInfo);
    }



}
