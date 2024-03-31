package com.lessons.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ConnectorToRedis {

    private static JedisPool jedisPool;

    private static  JedisPool getInstance(){
        if(jedisPool == null){
            jedisPool = new JedisPool("localhost", 6379);

        }
        return jedisPool;
    }

    public static  Jedis getResourses(){
        return getInstance().getResource();
    }
}
