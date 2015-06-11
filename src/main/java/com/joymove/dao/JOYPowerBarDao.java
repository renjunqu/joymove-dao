package com.joymove.dao;
import com.joymove.entity.*;

import java.util.*;
import java.math.*;



public interface JOYPowerBarDao  extends JOYBaseDao<JOYPowerBar> {
    List<JOYPowerBar>  getPowerBarByScope(Map<String, Object> likeCondition);

}
