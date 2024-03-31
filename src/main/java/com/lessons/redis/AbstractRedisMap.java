package com.lessons.redis;

import java.util.*;
import java.util.stream.Collectors;


public abstract class AbstractRedisMap<K, V> implements Map<K, V> {

    @Override
    public int size() {
        try (var jedis = ConnectorToRedis.getResourses()) {
            return jedis.keys("*").size();
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        String keyStr = key.toString();
        try (var jedis = ConnectorToRedis.getResourses()) {
            return jedis.keys(keyStr).stream().anyMatch(e -> e.equals(keyStr));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean containsValue(Object value) {
        try (var jedis = ConnectorToRedis.getResourses()) {
            return jedis.mget(jedis.keys("*")
                            .toArray(new String[0]))
                    .stream()
                    .anyMatch(e -> e.equals(value.toString()));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
