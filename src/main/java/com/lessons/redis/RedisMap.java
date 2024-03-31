package com.lessons.redis;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
public final class RedisMap extends AbstractRedisMap<String, String> {
    @Override
    public String get(Object key) {
        try (var jedis = ConnectorToRedis.getResourses()) {
            var value = jedis.get(key.toString());
            if (value == null) {
                throw new NullPointerException(String.format("Невозможно преобразовать значение для key = %s", key));
            }
            return value;
        } catch (Exception e) {
            throw new NoSuchElementException(String.format("Value with key= %s not found", key.toString()), e);
        }

    }

    @Override
    public String put(String key, String value) {
        try (var jedis = ConnectorToRedis.getResourses()) {
            jedis.set(key, value);
            return value;
        } catch (Exception e) {
            throw new NoSuchElementException(String.format("Value with key= %s not found", key), e);
        }
    }
    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        try (var jedis = ConnectorToRedis.getResourses()) {
            for(Entry<? extends  String,? extends  String> entry : m.entrySet()){
                jedis.set(entry.getKey(), entry.getValue());
            }
        } catch (Exception e) {
            throw new NoSuchElementException(String.format("Value for map %s not puts", m.toString()), e);
        }
    }
    @Override
    public Set<String> keySet() {
        try (var jedis = ConnectorToRedis.getResourses()) {
            return new HashSet<>(jedis.keys("*"));
        } catch (Exception e) {
            return new HashSet<>();
        }
    }

}
