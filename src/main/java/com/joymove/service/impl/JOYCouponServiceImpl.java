package com.joymove.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYBaseDao;
import com.joymove.dao.JOYCouponDao;
import com.joymove.dao.JOYDriverLicenseDao;
import com.joymove.entity.JOYCoupon;
import com.joymove.entity.JOYDriverLicense;
import com.joymove.service.JOYCouponService;
import com.joymove.service.JOYDriverLicenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("JOYCouponService")
public class JOYCouponServiceImpl extends JOYBaseServiceImpl<JOYCoupon> implements JOYCouponService  {

    final static Logger logger = LoggerFactory.getLogger(JOYCouponServiceImpl.class);

    @Autowired
    private JOYCouponDao  joyCouponDao;

    public JOYBaseDao getBaseDao() {
        return joyCouponDao;
    }

    public Class<JOYCoupon> getEntityClass() {
        return JOYCoupon.class;
    }

    @Override
    public List<JOYCoupon> getCouponById(Long[] cIds) {
        ArrayList<JOYCoupon> results = new ArrayList<JOYCoupon>();
        JOYCoupon couponFilter = new JOYCoupon();
        Map<String, Object> dataMap = new HashMap<String, Object>();

        for(Long id:cIds) {
            couponFilter.id = Integer.parseInt(String.valueOf(id));
            dataMap.put("filter",couponFilter);
            List<Map<String,Object>> couponMapL  = joyCouponDao.getPagedRecordList(dataMap);
            if(couponMapL.size()>0) {
                JOYCoupon coupon = new JOYCoupon();
                coupon.fromMap(couponMapL.get(0));
                results.add(coupon);
            }
        }
        return results;
    }

    @Override
    public void deleteCouponById(Long[] cIds,Integer orderId) {
            // TODO Auto-generated method stub
            JOYCoupon couponFilter = new JOYCoupon();
            JOYCoupon couponNew = new JOYCoupon();
            couponNew.delMark = JOYCoupon.DELMARK;
            couponNew.orderId = orderId;
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("value", couponNew);
            for (Long id : cIds) {
                couponFilter.id = Integer.parseInt(String.valueOf(id));
                dataMap.put("filter", couponFilter);
                joyCouponDao.updateRecord(dataMap);
            }
    }

}
