package com.lessons;

import com.lessons.CSV.CSVWriterReflection;
import com.lessons.CSV.CSVWriter;
import com.lessons.redis.AbstractRedisMap;
import com.lessons.redis.RedisMap;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        AbstractRedisMap<String, String> redisMap = new RedisMap();
        System.out.println(redisMap.size());
        System.out.println(redisMap.isEmpty());
        System.out.println(redisMap.get("foo"));
        System.out.println(redisMap.put("mama", "father"));
    }
}