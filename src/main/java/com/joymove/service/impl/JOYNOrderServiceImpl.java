package com.joymove.service.impl;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import com.joymove.dao.JOYBaseDao;
import com.joymove.dao.JOYNCarDao;
import com.joymove.entity.JOYNCar;
import com.joymove.entity.JOYReserveOrder;
import com.joymove.redis.RedisCmd;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.futuremove.cacheServer.entity.Car;
import com.futuremove.cacheServer.service.CarService;
import com.futuremove.cacheServer.utils.ConfigUtils;
import com.futuremove.cacheServer.utils.HttpPostUtils;
import com.joymove.dao.JOYOrderDao;
import com.joymove.dao.JOYReserveOrderDao;
import com.joymove.entity.JOYOrder;
import com.joymove.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("JOYNOrderService")
public class JOYNOrderServiceImpl extends JOYBaseServiceImpl<JOYOrder> implements JOYNOrderService {

    final static Logger logger = LoggerFactory.getLogger(JOYNOrderServiceImpl.class);

    public static ReentrantLock orderIdLock = new ReentrantLock();

    public static DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
    public static DateFormat timeFormat = new SimpleDateFormat("HH.mm");

    @Autowired
    private JOYOrderDao   joyOrderDao;

    @Autowired
    private JOYNCarDao joynCarDao;

    public JOYBaseDao getBaseDao() {
        return joyOrderDao;
    }

    public Class<JOYOrder> getEntityClass() {
        return JOYOrder.class;
    }

    public boolean createNewOrder(JOYOrder order) {
        boolean  success = false;
        Map<String,Object> likeCondtion = new HashMap<String,Object>();
        try {
            orderIdLock.lock();
            RedisCmd redisCmd = new RedisCmd();
            JSONObject lastOrderDate = redisCmd.getJson("lastOrderDate");
            JSONObject lastOrderCount = redisCmd.getJson("lastOrderCount");
            String dateStr = dateFormat.format(new Date(System.currentTimeMillis()));
            String timeStr = timeFormat.format(new Date(System.currentTimeMillis()));
            long count = 1;

            if(lastOrderDate==null||lastOrderCount==null) {
            /*    lastOrderCount = new JSONObject();
                lastOrderDate  = new JSONObject();
                lastOrderCount.put("count",0);
                lastOrderDate.put("date",timeStr);
            */
            } else if(dateStr.equals(String.valueOf(lastOrderDate.get("date")))) {
                  count = Long.parseLong(String.valueOf(lastOrderCount.get("count"))) + 1;
            } else {

            }


            //generate the uuid of order  ==================================
            String orderUUID = "FC"+String.format("%04d",count)+dateStr+timeStr+ order.carLicenseNum;
            logger.debug("orderUUID is "+orderUUID);
            order.uuid = orderUUID;
            joyOrderDao.insertRecord(order);
            if(lastOrderCount==null||lastOrderDate==null) {
                lastOrderCount = new JSONObject();
                lastOrderDate = new JSONObject();
            }
            //update the data in redis  =======================================
            lastOrderDate.put("date",dateStr);
            lastOrderCount.put("count",count);
            redisCmd.saveJson("lastOrderCount",lastOrderCount.toJSONString());
            redisCmd.saveJson("lastOrderDate",lastOrderDate.toJSONString());
            success = true;
        } catch(Exception e) {
            logger.error("exception: ",e);
        } finally {
            if(orderIdLock.getHoldCount()>0)
                orderIdLock.unlock();
        }
        return success;
    }

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:test.xml");

        JOYNOrderService service = (JOYNOrderService)context.getBean("JOYNOrderService");
        JOYOrder order = new JOYOrder();
        service.createNewOrder(order);




    }


}
