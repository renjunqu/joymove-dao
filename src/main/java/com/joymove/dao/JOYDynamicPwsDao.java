package com.joymove.dao;



import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYDynamicPws;
import com.joymove.entity.JOYUser;

public interface JOYDynamicPwsDao {
	
	 void insertDynamicPws(JOYDynamicPws code);
	
	 List<JOYDynamicPws> getDynamicPws(Map<String,Object> likeCondition);
	
}
