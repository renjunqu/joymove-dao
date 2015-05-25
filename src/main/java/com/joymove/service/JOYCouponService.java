package com.joymove.service;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYCoupon;

public interface JOYCouponService {
	
	public List<JOYCoupon> getJOYCoupon(Map<String, Object> likeCondition);
	
	public void insertJOYCoupon(JOYCoupon joy);


	public List<JOYCoupon> getCouponById(Long[] cIds);

	public void deleteCouponById(Long[] cIds);
	
	
}
