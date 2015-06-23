package com.joymove.redis;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.Map;

/**
 * Created by jessie on 2015/5/25.
 */

@Service("RedisCmd")
public class RedisCmd {

    public void saveJson(String key,String jsonStr){
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "123.57.151.176");
        Jedis jedis =  pool.getResource();
        jedis.set(key,jsonStr);
        pool.returnResource(jedis);
    }

    public JSONObject getJson(String key){
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "123.57.151.176");
        Jedis jedis = pool.getResource();
        String jstr = jedis.get(key);
        pool.returnResource(jedis);
        try {
            JSONParser parser = new JSONParser();
            return (JSONObject)parser.parse(jstr);
        } catch (Exception e) {
            return null;
        }
    }



}
