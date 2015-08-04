package com.joymove.postgres.dao;
import com.joymove.postgres.dao.JOYBaseDao;
import org.apache.velocity.Template;
import org.mybatis.scripting.velocity.VelocityFacade;

/**
 * Created by qurj on 15/8/4.
 */
public class JOYPowerBarDao extends  JOYBaseDao {
    public static String queryNearyByCount = "SELECT count(id) from \"JOY_PowerBar\" " +
            "where (location <-> ST_GeomFromText('POINT(${longitude} ${latitude})',4326)) < ${scope}/111045::float limit 10000;";
    public static String queryNearyByBars = "SELECT id,ST_AsText(location) as location,extendinfo,desp,\"testArr\" from \"JOY_PowerBar\" " +
            "ORDER BY location <-> ST_GeomFromText('POINT(${longitude} ${latitude})',4326) limit ${limit} offset ${start} ;";



    public static Template countTemplate;
    public static Template barTemplate;

    static
    {
        JOYPowerBarDao.countTemplate = (Template) VelocityFacade.compile(JOYPowerBarDao.queryNearyByCount, "queryCount");
        JOYPowerBarDao.barTemplate = (Template) VelocityFacade.compile(JOYPowerBarDao.queryNearyByBars, "queryBars");
    }
}
