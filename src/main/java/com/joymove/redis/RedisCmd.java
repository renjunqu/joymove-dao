package com.joymove.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.Map;

/**
 * Created by jessie on 2015/5/25.
 */
public class RedisCmd {
    public static JedisPool pool = new JedisPool(new JedisPoolConfig(), "123.57.151.176");

    public void saveJson(String key,String jsonStr){
        Jedis jedis =  pool.getResource();
        jedis.set(key,jsonStr);

    }

    public JSONObject getJson(String key){
        Jedis jedis = pool.getResource();
        String jstr = jedis.get(key);

        try {
            JSONParser parser = new JSONParser();
            return (JSONObject)parser.parse(jstr);
        } catch (Exception e) {
            return null;
        }
    }



}
