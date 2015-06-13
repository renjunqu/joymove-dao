package com.joymove.service;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYCoupon;
import com.joymove.entity.JOYUser;

public interface JOYCouponService extends  JOYBaseService<JOYCoupon>  {

    public List<JOYCoupon> getCouponById(Long[] cIds);

    public void deleteCouponById(Long[] cIds,Integer orderId);

}
