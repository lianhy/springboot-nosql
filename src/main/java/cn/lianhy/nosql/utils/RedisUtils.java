package cn.lianhy.nosql.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 移除某key
     * @param key
     */
    public void remove(String key){
        if (exists(key)){
            stringRedisTemplate.delete(key);
        }
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean exists(String key){
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 存储key-value
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, String value){
        boolean result = false;
        try {
            stringRedisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 存储有时常的key-value
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public boolean set(String key, String value, long expireTime){
        boolean result = false;
        try {
            stringRedisTemplate.opsForValue().set(key,value,expireTime,TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取key对应的value
     * @param key
     * @return
     */
    public String get(String key){
        String str="";
        try {
            str=stringRedisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 头部添加值
     * @param key
     * @param value
     * @return
     */
    public boolean lpush(String key, String value){
        boolean result = false;
        try {
            stringRedisTemplate.opsForList().leftPush(key,value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取集合
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> range(String key, int start, int end){
        List<String> list=new ArrayList<>();
        try {
            list=stringRedisTemplate.opsForList().range(key,(long)start,(long)end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 从尾部添加值
     * @param key
     * @param value
     * @return
     */
    public boolean rpush(String key, String value){
        boolean result = false;
        try {
            stringRedisTemplate.opsForList().rightPush(key,value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
