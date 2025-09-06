package com.drx.starter.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {


    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void setValue(String key, Object value, Long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Object getValueTimeout(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public void setHashValue(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    public Object getHashValue(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    public Boolean remove(String key) {
        return redisTemplate.delete(key);
    }

}
