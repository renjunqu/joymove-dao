package com.joymove.dao;


import java.util.List;
import java.util.Map;
import com.joymove.entity.JOYSeed;

public interface JOYSeedDao {

     List<JOYSeed> getNeededSeed(Map<String,Object> likeCondition);

     void deleteSeed(JOYSeed seed);

     void createSeed(JOYSeed seed);
}
