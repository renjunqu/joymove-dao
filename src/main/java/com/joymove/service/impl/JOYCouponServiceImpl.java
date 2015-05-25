package com.joymove.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYCouponDao;
import com.joymove.entity.JOYCoupon;
import com.joymove.service.JOYCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("JOYCouponService")
public class JOYCouponServiceImpl implements JOYCouponService{
	@Autowired
	private JOYCouponDao joycouponDao;

	@Override
	public List<JOYCoupon> getJOYCoupon(Map<String, Object> likeCondition) {
		// TODO Auto-generated method stub
		return joycouponDao.getJOYCoupon(likeCondition);
	}

	@Override
	public void insertJOYCoupon(JOYCoupon joy) {
		// TODO Auto-generated method stub
		joycouponDao.insertJOYCoupon(joy);
	}



	@Override
	public List<JOYCoupon> getCouponById(Long[] cIds) {
		ArrayList<JOYCoupon> results = new ArrayList<JOYCoupon>();
		for(Long id:cIds) {
			
			JOYCoupon coupon_ret = joycouponDao.getCouponById(id);
			if(coupon_ret!=null) {
				results.add(coupon_ret);
			}
		}
		return results;
	}

	@Override
	public void deleteCouponById(Long[] cIds) {
		// TODO Auto-generated method stub
			for(Long id:cIds) {	
				joycouponDao.deleteCouponById(id);
			}
	}
	
	

	
	

}
