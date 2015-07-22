package com.joymove.postgres.entity;

import com.joymove.postgres.entity.JOYBase;
import org.json.simple.JSONObject;
import org.mongodb.morphia.geo.Geometry;

/**
 * Created by qurj on 15/7/22.
 */
public class JOYPark extends JOYBase {
    public Long id;
    public String desp;
    public JSONObject extendinfo;
    public Geometry   location;
}
