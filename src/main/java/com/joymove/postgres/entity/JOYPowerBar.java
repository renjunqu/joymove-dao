package com.joymove.postgres.entity;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.mybatis.scripting.velocity.InDirective;
import org.postgis.Geometry;
import org.postgis.PGgeometry;
import org.postgresql.geometric.PGpoint;

import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jessie on 2015/7/21.
 */
public class JOYPowerBar extends  JOYBase  {
    public Long id;
    public String desp;
    public Geometry location;
    public JSONObject extendinfo;
    public List<Integer> testArr;


}
