package com.joymove.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joymove.dao.JOYBaseDao;
import com.joymove.entity.JOYBase;
import com.joymove.entity.JOYUser;



public interface JOYUserService extends  JOYBaseService<JOYUser>  {

     String checkUserState(JOYUser user);
}
