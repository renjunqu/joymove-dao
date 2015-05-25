package com.joymove.dao;



import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYDynamicPws;
import com.joymove.entity.JOYUser;

public interface JOYDynamicPwsDao {
	
	public void insertDynamicPws(JOYDynamicPws registercode);
	
	public List<JOYDynamicPws> getDynamicPws(Map<String,Object> likeCondition);
	
}
