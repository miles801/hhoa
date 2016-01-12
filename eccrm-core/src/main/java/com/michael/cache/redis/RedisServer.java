package com.michael.cache.redis;

import redis.clients.jedis.ShardedJedis;

/**
 * Redis相关的服务接口
 *
 * @author Michael
 */
public interface RedisServer {
    /**
     * 获取Redis客户端对象
     */
    ShardedJedis getRedisClient();

}
