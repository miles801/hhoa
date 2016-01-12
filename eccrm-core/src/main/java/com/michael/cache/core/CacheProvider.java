package com.michael.cache.core;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 缓存供应商
 * 提供缓存服务
 *
 * @author Michael
 */
public interface CacheProvider {
    /**
     * 往缓存中放入一个字符串
     *
     * @param key   唯一标示
     * @param value 对应的值
     */
    void put(String key, String value);

    /**
     * 往缓存中指定key的集合中放入一个或多个值
     *
     * @param key    唯一标识
     * @param values 对应的值
     */
    void addToList(String key, String... values);

    /**
     * 往缓存中指定key的集合中放入一个或多个值
     *
     * @param key    唯一标识
     * @param values 对应的值
     */
    void addToSet(String key, String... values);

    /**
     * 往缓存中让如一个键值对
     *
     * @param key   唯一标识
     * @param field 键
     * @param value 值
     */
    void put(String key, String field, String value);

    /**
     * 往缓存中放入一个键值对集合
     *
     * @param key    唯一标识
     * @param values 键值对集合
     */
    void put(String key, Map<String, String> values);

    /**
     * 往缓存中放入一个键值对，并且在指定毫秒数后失效
     *
     * @param key     唯一标识
     * @param value   对应的值
     * @param seconds 从当前时间之后的多少秒过期
     */
    void put(String key, String value, int seconds);

    /**
     * 从缓存中删除指定key的字符串缓存
     *
     * @param key 唯一标示
     */
    void removeValue(String key);

    /**
     * 从缓存中删除指定key的集合中，指定范围内的值
     *
     * @param key   唯一标示
     * @param start 开始位置，从0开始，包含
     * @param end   结束位置，不包含当前位置，如果是末尾，则使用-1
     */
    void removeList(String key, int start, int end);

    /**
     * 清空指定集合的缓存
     *
     * @param key 唯一标识
     */
    void clearList(String key);

    /**
     * 从缓存中删除指定key的set集合中指定的值
     *
     * @param key 唯一标示
     */
    void removeSet(String key, String... values);

    /**
     * 从缓存中清空指定key的set集合
     *
     * @param key 唯一标识
     */
    void clearSet(String key);

    /**
     * 从缓存中移除指定key的指定名称的键值对
     *
     * @param key    唯一标识
     * @param fields 键的名称数组
     */
    void removeMap(String key, String... fields);

    /**
     * 从缓存中清空指定key的键值对
     *
     * @param key 唯一标识
     */
    void clearMap(String key);

    /**
     * 获得指定Key的值
     *
     * @param key 唯一标识
     * @return 缓存的值
     */
    String getValue(String key);

    /**
     * 从缓存中指定key的集合中弹出一条数据
     *
     * @param key 唯一标识
     */
    String popSet(String key);

    /**
     * 从缓存中获取指定key的Set集合数据
     *
     * @param key 唯一标识
     * @return Set字符串集合
     */
    Set<String> getSet(String key);

    /**
     * 从缓存中指定key的集合中弹出一条数据
     *
     * @param key 唯一标识
     */
    String popList(String key);

    /**
     * 从缓存中获取指定key的List集合数据
     *
     * @param key 唯一标识
     * @return List字符串集合
     */
    List<String> getList(String key);

    /**
     * 从缓存中获取指定key的键值对集合数据
     *
     * @param key 唯一标识
     * @return 缓存的键值对
     */
    Map<String, String> getMap(String key);

    /**
     * 缓存中获取指定key的键值对中指定属性名称的值
     *
     * @param key   唯一标识
     * @param field 键值对的名称
     * @return 键值对的值
     */
    String getMapValue(String key, String field);
}
