package com.michael.cache.redis;

import com.michael.cache.core.CacheProvider;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 基于Redis的缓存供应商
 * 注意：需要注入RedisServer示例
 *
 * @author Michael
 */
@Service
public class RedisCacheProvider implements CacheProvider {

    @Resource
    private RedisServer redisServer;

    @Override
    public String getValue(String key) {
        ShardedJedis redisClient = redisServer.getRedisClient();
        String value = redisClient.get(key);
        redisClient.close();
        return value;
    }

    @Override
    public void removeValue(String key) {
        ShardedJedis redisClient = redisServer.getRedisClient();
        redisClient.del(key);
        redisClient.close();
    }

    @Override
    public void put(String key, String value) {
        ShardedJedis redisClient = redisServer.getRedisClient();
        redisClient.set(key, value);
        redisClient.close();
    }

    @Override
    public void put(String key, String value, int seconds) {
        ShardedJedis redisClient = redisServer.getRedisClient();
        redisClient.set(key, value);
        redisClient.expire(key, seconds);
        redisClient.close();
    }


    @Override
    public void put(String key, String field, String value) {
        ShardedJedis redisClient = redisServer.getRedisClient();
        redisClient.hset(key, field, value);
        redisClient.close();
    }

    @Override
    public void put(String key, Map<String, String> values) {
        for (Map.Entry<String, String> entry : values.entrySet()) {
            put(key, entry.getKey(), entry.getValue());
        }
    }


    @Override
    public List<String> getList(String key) {
        ShardedJedis redisClient = redisServer.getRedisClient();
        List<String> data = redisClient.lrange(key, 0, -1);
        redisClient.close();
        return data;
    }

    @Override
    public Set<String> getSet(String key) {
        ShardedJedis redisClient = redisServer.getRedisClient();
        Set<String> data = redisClient.zrange(key, 0, -1);
        redisClient.close();
        return data;
    }


    @Override
    public void removeList(String key, int start, int end) {
        if (start >= end) {
            return;
        }
        ShardedJedis redisClient = redisServer.getRedisClient();
        // 获得移除后的结果集
        List<String> needRemove = redisClient.lrange(key, start, end);
        List<String> all = redisClient.lrange(key, 0, -1);
        all.removeAll(needRemove);
        // 删除原有缓存
        redisClient.del(key);
        // 重新添加缓存
        addToList(key, all.toArray(new String[all.size()]));
        redisClient.close();
    }

    @Override
    public void clearList(String key) {
        ShardedJedis redisClient = redisServer.getRedisClient();
        redisClient.del(key);
        redisClient.close();
    }

    @Override
    public void removeSet(String key, String... values) {
        ShardedJedis redisClient = redisServer.getRedisClient();
        redisClient.srem(key, values);
        redisClient.close();
    }

    @Override
    public void clearSet(String key) {
        ShardedJedis redisClient = redisServer.getRedisClient();
        redisClient.del(key);
        redisClient.close();
    }

    @Override
    public void removeMap(String key, String... fields) {
        ShardedJedis redisClient = redisServer.getRedisClient();
        redisClient.zrem(key, fields);
        redisClient.close();
    }

    @Override
    public void clearMap(String key) {
        ShardedJedis redisClient = redisServer.getRedisClient();
        redisClient.del(key);
        redisClient.close();
    }

    @Override
    public Map<String, String> getMap(String key) {
        ShardedJedis redisClient = redisServer.getRedisClient();
        Map<String, String> data = redisClient.hgetAll(key);
        redisClient.close();
        return data;
    }

    @Override
    public String getMapValue(String key, String field) {
        ShardedJedis redisClient = redisServer.getRedisClient();
        String value = redisClient.hget(key, field);
        redisClient.close();
        return value;
    }

    @Override
    public void addToSet(String key, String... values) {
        ShardedJedis redisClient = redisServer.getRedisClient();
        redisClient.sadd(key, values);
        redisClient.close();
    }

    @Override
    public void addToList(String key, String... values) {
        ShardedJedis redisClient = redisServer.getRedisClient();
        redisClient.lpush(key, values);
        redisClient.close();
    }

    @Override
    public String popList(String key) {
        ShardedJedis redisClient = redisServer.getRedisClient();
        String value = redisClient.lpop(key);
        redisClient.close();
        return value;
    }

    @Override
    public String popSet(String key) {
        ShardedJedis redisClient = redisServer.getRedisClient();
        String value = redisClient.spop(key);
        redisClient.close();
        return value;
    }
}
