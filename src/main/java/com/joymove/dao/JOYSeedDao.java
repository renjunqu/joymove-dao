package com.joymove.dao;


import java.util.List;
import java.util.Map;
import com.joymove.entity.JOYSeed;

public interface JOYSeedDao {

    public List<JOYSeed> getNeededSeed(Map<String,Object> likeCondition);

    public void deleteSeed(JOYSeed seed);

    public void createSeed(JOYSeed seed);
}
