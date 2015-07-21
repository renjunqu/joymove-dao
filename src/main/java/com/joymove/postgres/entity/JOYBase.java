package com.joymove.postgres.entity;

import org.json.simple.JSONObject;
import org.postgis.Geometry;
import org.postgis.PGgeometry;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jessie on 2015/7/21.
 */
public class JOYBase {

    public static JSONObject toJSON(JOYBase pb) {
        JSONObject jsonObject = new JSONObject();
        Field[] fs = JOYPowerBar.class.getFields();
        for (Field f : fs) {
            try {
                String fName = f.getName();
                if(f.get(pb)!=null) {
                    if (f.getType().equals(Geometry.class)) {
                        Geometry geo = (Geometry) f.get(pb);
                        switch (geo.getType()) {
                            case Geometry.POINT:
                                jsonObject.put("longitude",geo.getPoint(0).getX());
                                jsonObject.put("latitude",geo.getPoint(0).getY());
                                break;
                            default:
                                break;
                        }
                    } else if(f.getType().equals(List.class)){
                        jsonObject.put(f.getName(), f.get(pb));
                    } else {
                        jsonObject.put(f.getName(), f.get(pb));
                    }
                }
            }catch(Exception e){

            }
        }
        return jsonObject;
    }

}
