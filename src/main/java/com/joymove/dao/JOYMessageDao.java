package com.joymove.dao;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYMessage;



public interface JOYMessageDao {
	
	
	public List<JOYMessage> getJOYMessageByID(Map<String,Object> likeCondition);
	
	public List<JOYMessage> getJOYBroadcastMessage();
}
