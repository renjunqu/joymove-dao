package com.joymove.dao;

import java.util.List;
import java.util.Map;


import com.joymove.entity.JOYCoupon;

public interface JOYCouponDao {
	
	
	public List<JOYCoupon> getJOYCoupon(Map<String, Object> likeCondit);
	
	public void insertJOYCoupon(JOYCoupon joy);

	public JOYCoupon getCouponById(Long couponId);

	public void deleteCouponById(Long couponId);
}
