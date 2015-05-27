package com.joymove.dao;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYMessage;



public interface JOYMessageDao {
	
	
	 List<JOYMessage> getJOYMessageByID(Map<String,Object> likeCondition);
	
	 List<JOYMessage> getJOYBroadcastMessage();
}
