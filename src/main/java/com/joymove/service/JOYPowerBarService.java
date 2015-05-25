package com.joymove.service;
import com.joymove.entity.*;
import java.util.*;
import java.math.*;

public interface JOYPowerBarService {

	public List<JOYPowerBar>  getPowerBarByScope(Map<String, Object> likeCondition);

}
