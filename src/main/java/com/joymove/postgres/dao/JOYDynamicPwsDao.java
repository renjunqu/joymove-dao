package com.joymove.postgres.dao;

import com.joymove.postgres.dao.JOYBaseDao;
import org.apache.velocity.Template;
import org.mybatis.scripting.velocity.VelocityFacade;

/**
 * Created by qurj on 15/8/4.
 */
public class JOYDynamicPwsDao extends JOYBaseDao {

    public static String insertPws = "insert into \"JOY_DynamicPws\" " +
            "(\"mobileNo\",code) values ('${mobileNo}','${code}')";
    public static String getPws = "SELECT * from \"JOY_DynamicPws\" " +
            "where \"mobileNo\" = '${mobileNo}' order by id desc limit 1;";



    public static Template insertPwsTemplate;
    public static Template getPwsTemplate;

     static
     {
        JOYDynamicPwsDao.insertPwsTemplate = (Template) VelocityFacade.compile(JOYDynamicPwsDao.insertPws, "insertPws");
        JOYDynamicPwsDao.getPwsTemplate = (Template) VelocityFacade.compile(JOYDynamicPwsDao.getPws, "getPws");
     }

}
