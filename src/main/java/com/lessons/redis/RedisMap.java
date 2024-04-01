package com.lessons.redis;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
public final class RedisMap extends AbstractRedisMap<String, String> {
    @Override
    public String get(@NonNull Object key) {
        if (!(key instanceof String)) {
            throw new IllegalArgumentException(String.format("Невозможно преобразовать key = %s в строку", key));
        }
        try (var jedis = ConnectorToRedis.getResourses()) {
            var value = jedis.get((String) key);
            if (value == null) {
                throw new NoSuchElementException(String.format("Value with key= %s not found", key));
            }
            return value;
        }

    }

    @Override
    public String put(@NonNull String key, @NonNull String value) {
        try (var jedis = ConnectorToRedis.getResourses()) {
            jedis.set(key, value);
            return value;
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Value with key= %s not found", key), e);
        }
    }

    @Override
    public void putAll(@NonNull Map<? extends String, ? extends String> m) {
        try (var jedis = ConnectorToRedis.getResourses()) {
            for (Entry<? extends String, ? extends String> entry : m.entrySet()) {
                jedis.set(entry.getKey(), entry.getValue());
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Value for map %s not puts", m), e);
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
