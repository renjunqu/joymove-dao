package com.joymove.service.impl;

import com.joymove.dao.JOYBaseDao;
import com.joymove.dao.JOYCarClusterDao;
import com.joymove.dao.JOYCarDao;
import com.joymove.entity.JOYCar;
import com.joymove.entity.JOYCarCluster;
import com.joymove.entity.JOYOrder;
import com.joymove.entity.JOYPowerBar;
import com.joymove.service.JOYCarClusterService;
import com.joymove.service.JOYCarService;
import com.joymove.service.JOYNOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by qurj on 15/6/23.
 */

@Service("JOYCarClusterService")
public class JOYCarClusterServiceImpl  extends JOYBaseServiceImpl<JOYCarCluster> implements JOYCarClusterService  {

    final static Logger logger = LoggerFactory.getLogger(JOYCarClusterServiceImpl.class);

    @Autowired
    private JOYCarClusterDao joyCarClusterDao;


    public JOYBaseDao getBaseDao() {
        return joyCarClusterDao;
    }

    public Class<JOYCarCluster> getEntityClass() {
        return JOYCarCluster.class;
    }

    public  List<JOYCarCluster> getCarClusterByScope(Map<String, Object> likeCondition) {
          return joyCarClusterDao.getCarClusterByScope(likeCondition);
    }

    public  static void main(String [] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:test.xml");
        Map<String,Object> likeCondition = new HashMap<String, Object>();


        JOYCarClusterService service = (JOYCarClusterService)context.getBean("JOYCarClusterService");
        likeCondition.put("latitude", 22.735098);
        likeCondition.put("longitude",114.075579);
        likeCondition.put("scope", 20000000L);
        List<JOYCarCluster> ccs = service.getCarClusterByScope(likeCondition);
        System.out.println(ccs.toString());

    }

}
