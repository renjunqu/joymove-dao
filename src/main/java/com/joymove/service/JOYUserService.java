package com.joymove.service;

import java.util.List;
import java.util.Map;

import com.joymove.entity.JOYBase;
import com.joymove.entity.JOYUser;



public interface JOYUserService extends  JOYBaseService<JOYUser>  {

    public String checkUserState(JOYUser user);
}
