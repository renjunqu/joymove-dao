package com.joymove.dao;

import java.util.List;
import java.util.Map;


import com.joymove.entity.JOYCoupon;

public interface JOYCouponDao {
	
	
	 List<JOYCoupon> getJOYCoupon(Map<String, Object> likeCondition);
	
	 void insertJOYCoupon(JOYCoupon coupon);

	 JOYCoupon getCouponById(Long couponId);

	 void deleteCouponById(Long couponId);
}
