package com.joymove.service.impl;

import com.joymove.dao.JOYBaseDao;
import com.joymove.dao.JOYWXJSDataDao;
import com.joymove.entity.JOYSeed;
import com.joymove.entity.JOYWXJSData;
import com.joymove.service.JOYSeedService;

import java.util.Map;
import java.util.Date;
import java.util.List;

import com.joymove.dao.JOYSeedDao;
import com.joymove.dao.JOYCouponDao;
import com.joymove.entity.JOYCoupon;
import com.joymove.service.JOYWXJSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("JOYSeedService")
public class JOYSeedServiceImpl extends JOYBaseServiceImpl<JOYSeed> implements JOYSeedService {
    final static Logger logger = LoggerFactory.getLogger(JOYSeedServiceImpl.class);
    @Autowired
    private JOYSeedDao joySeedDao;

    @Autowired
    private  JOYCouponDao joyCouponDao;

    public JOYBaseDao getBaseDao() {
        return joySeedDao;
    }

    public Class<JOYSeed> getEntityClass() {
        return JOYSeed.class;
    }

    public void exchangeCoupon(JOYSeed seed) {
        //first add the coupon for the user
        JOYCoupon coupon = new JOYCoupon();
        coupon.couponNum = (seed.balance);
        coupon.mobileNo = (seed.mobileNo);
        Date overdueTime = new Date(System.currentTimeMillis()+1000*3600*24*365);
        coupon.overdueTime = (overdueTime);
        joyCouponDao.insertRecord(coupon);
        //then update the seed's status
        JOYSeed seedNew = new JOYSeed();
        seedNew.status = JOYSeed.status_seed_used;
        this.updateRecord(seedNew,seed);
    }
}
