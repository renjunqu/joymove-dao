package com.joymove.postgres.entity;

import org.postgis.Geometry;
import org.postgis.PGgeometry;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.security.Timestamp;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by jessie on 2015/7/21.
 */
public class JOYRowMapper  implements RowMapper {
    public Class<? extends JOYBase> rowClass;

    public JOYRowMapper(Class<?extends JOYBase> rowClass) {
        this.rowClass = rowClass;
    }


    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Field[] fs = rowClass.getFields();
        JOYBase rowObj = new JOYBase();
        //rowClass
        try {
            rowObj =  rowClass.newInstance();
            for (Field f : fs) {
                String fName = f.getName();
                try {
                    if(f.getType().equals(Integer.class) || f.getType().equals(Long.class)) {
                        f.set(rowObj,resultSet.getLong(f.getName()));
                    } else if(f.getType().equals(String.class)) {
                        f.set(rowObj,resultSet.getString(f.getName()));
                    } else if(f.getType().equals(List.class)) {
                        Array sqlArray = resultSet.getArray(f.getName());
                        //pb.testArr = Arrays.asList((Integer[])sqlArray.getArray());
                        f.set(rowObj, Arrays.asList((Integer[]) sqlArray.getArray()));
                    }  else if(f.getType().equals(Geometry.class)) {
                        String geoStr = resultSet.getString(f.getName());
                        f.set(rowObj, PGgeometry.geomFromString(geoStr));
                    } else if(f.getType().equals(Float.class)||f.getType().equals(Double.class)) {
                        f.set(rowObj,resultSet.getDouble(f.getName()));
                    } else if(f.getType().equals(Timestamp.class)||f.getType().equals(Date.class)){
                         f.set(rowObj,resultSet.getTimestamp(f.getName()));
                    }
                } catch(Exception e) {
                    //sdfsdf
                }
            }
        } catch(Exception e){

        }
        return rowObj;
    }

}
