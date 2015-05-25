package com.joymove.service.impl;

import com.joymove.entity.JOYSeed;
import com.joymove.service.JOYSeedService;

import java.util.Map;
import java.util.Date;
import java.util.List;

import com.joymove.dao.JOYSeedDao;
import com.joymove.dao.JOYCouponDao;
import com.joymove.entity.JOYCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("JOYSeedService")
public class JOYSeedServiceImpl implements JOYSeedService {
    @Autowired
    private JOYSeedDao joySeedDao;
    @Autowired
    private JOYCouponDao joyCouponDao;


    /**** business proc ******/
    public void exchangeCoupon(JOYSeed seed) {
        //first add the coupon for the user
        JOYCoupon coupon = new JOYCoupon();
        coupon.couponNum = (seed.balance);
        coupon.mobileNo = (seed.mobileNo);
        Date overdueTime = new Date(System.currentTimeMillis()+1000*3600*24*365);
        coupon.overdueTime = (overdueTime);
        joyCouponDao.insertJOYCoupon(coupon);
        //then update the seed's status
        joySeedDao.deleteSeed(seed);
    }
    public JOYSeed getNeededSeed(Map<String,Object> likeCondition){

        List<JOYSeed> seeds =  joySeedDao.getNeededSeed(likeCondition);
        if(seeds.size()>=1){
            return seeds.get(0);
        } else {
            return null;
        }
    }

}
