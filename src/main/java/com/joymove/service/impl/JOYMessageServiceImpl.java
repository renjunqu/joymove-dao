package com.joymove.service.impl;

import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYMessageDao;
import com.joymove.entity.JOYMessage;
import com.joymove.service.JOYMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("JOYMessageService")
public class JOYMessageServiceImpl implements JOYMessageService{
	@Autowired
	private JOYMessageDao  joyMessageDao;
	
	@Override
	public List<JOYMessage> getJOYMessageById(Map<String,Object> likeCondition) {
		// TODO Auto-generated method stub
		List<JOYMessage> messages = joyMessageDao.getJOYMessageByID(likeCondition);
		return messages;
	}


	@Override
	public List<JOYMessage> getJOYBroadcastMessage() {
			
		 List<JOYMessage> broadcastMessages = joyMessageDao.getJOYBroadcastMessage();
		return broadcastMessages;
	}

	
	
}
