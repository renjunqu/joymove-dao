package com.joymove.service;
import com.joymove.dao.JOYPowerBarDao;
import com.joymove.entity.*;
import java.util.*;
import java.math.*;

public interface JOYPowerBarService  extends  JOYBaseService<JOYPowerBar>   {

    public List<JOYPowerBar>  getPowerBarByScope(Map<String, Object> likeCondition);


}
