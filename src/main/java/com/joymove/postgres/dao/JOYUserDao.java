package com.joymove.postgres.dao;
import com.joymove.postgres.dao.JOYBaseDao;
import org.apache.velocity.Template;
import org.mybatis.scripting.velocity.VelocityFacade;

/**
 * Created by qurj on 15/8/4.
 */
public class JOYUserDao  extends  JOYBaseDao {
    public static String addNewUser = " insert into \"JOY_User\" " +
            " (\"mobileNo\",userpwd) values ('${mobileNo}','${userpwd}') ";

    public static String getUserByProps = " select * from \"JOY_User\" " +
            " #where() " +
            " #if(${mobileNo}) and \"mobileNo\" = '${mobileNo}' #end " +
            " #end ";

    public static String updateUserProps = " update \"JOY_User\" " +
            " #mset() " +
            " #if(${authToken}) \"authToken\" = '${authToken}', #end " +
            " #if(${lastActiveTime}) \"lastActiveTime\" = to_timestamp(${lastActiveTime.getTime()}/1000), #end " +
            " #if(${userpwd}) userpwd = '${userpwd}', #end " +
            " #end " +
            " where id=${id} ";


    public static Template addNewUserTemplate;
    public static Template getUserByPropsTemplate;
    public static Template updateUserPropsTemplate;

    static
    {
        JOYUserDao.addNewUserTemplate = (Template) VelocityFacade.compile(JOYUserDao.addNewUser, "addNewUser");

        JOYUserDao.getUserByPropsTemplate = (Template)
                VelocityFacade.compile(JOYUserDao.getUserByProps,"getUserByProps");
        JOYUserDao.updateUserPropsTemplate =
                (Template)VelocityFacade.compile(JOYUserDao.updateUserProps,"updateUserProps");
    }


}
