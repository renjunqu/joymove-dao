package com.joymove.postgres.dao;
import com.joymove.postgres.dao.JOYBaseDao;
import org.apache.velocity.Template;
import org.mybatis.scripting.velocity.VelocityFacade;

/**
 * Created by qurj on 15/8/4.
 */
public class JOYParkDao extends  JOYBaseDao {

    public static String queryNearyByCount = "SELECT count(id) from \"JOY_Park\" " +
            "where (location <-> ST_GeomFromText('POINT(${longitude} ${latitude})',4326)) < ${scope}/111045::float limit 10000;";
    public static String queryNearyByParks = "SELECT id,ST_AsText(location) as location,extendinfo,desp from \"JOY_Park\" " +
            "ORDER BY location <-> ST_GeomFromText('POINT(${longitude} ${latitude})',4326) limit ${limit} offset ${start} ;";



    public static Template countTemplate;
    public static Template parkTemplate;

    static
    {
        //反正是单例模式，就放在实例的初始化块中好了。
         JOYParkDao.countTemplate = (Template) VelocityFacade.compile(JOYParkDao.queryNearyByCount, "queryCount");
        JOYParkDao.parkTemplate = (Template) VelocityFacade.compile(JOYParkDao.queryNearyByParks, "queryParks");
    }

}
