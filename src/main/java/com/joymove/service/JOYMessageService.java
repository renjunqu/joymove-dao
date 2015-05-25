package com.joymove.service;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYMessage;

public interface JOYMessageService {
	
	public List<JOYMessage> getJOYMessageById(Map<String,Object> likeCondition);
	public List<JOYMessage> getJOYBroadcastMessage();
}
