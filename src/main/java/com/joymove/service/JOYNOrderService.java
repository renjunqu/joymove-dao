package com.joymove.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import com.joymove.entity.JOYCar;
import com.joymove.entity.JOYUser;
import org.quartz.SchedulerException;

import com.futuremove.cacheServer.entity.Car;
import com.joymove.entity.JOYOrder;

public interface JOYNOrderService extends  JOYBaseService<JOYOrder>  {



    //use our own order uuid generator algorithm
    public boolean createNewOrder(JOYOrder order);


}
