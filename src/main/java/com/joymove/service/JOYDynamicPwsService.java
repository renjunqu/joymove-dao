package com.joymove.service;



import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYDynamicPws;

public interface JOYDynamicPwsService {
	public void insertDynamicPwse(JOYDynamicPws dynamicpws);
	
	public List<JOYDynamicPws> getDynamicPws(Map<String,Object> likeCondition);
}
