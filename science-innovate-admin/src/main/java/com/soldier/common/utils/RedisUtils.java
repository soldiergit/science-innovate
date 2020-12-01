package com.soldier.common.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @Author soldier
 * @Date 20-8-14 上午10:16
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description: Redis工具类
 */
@Component
public class RedisUtils {

    // 注入的这些bean，具体用途查看RedisConfig.class
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ValueOperations<String, String> valueOperations;
    @Autowired
    private HashOperations<String, String, Object> hashOperations;
    @Autowired
    private ListOperations<String, Object> listOperations;
    @Autowired
    private SetOperations<String, Object> setOperations;
    @Autowired
    private ZSetOperations<String, Object> zSetOperations;
    /**  默认过期时长（一天），单位：秒 */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**  不设置过期时长 */
    public final static long NOT_EXPIRE = -1;
    private final static Gson gson = new Gson();

    // 以下均只操作字符串类型数值，其它类型请自己添加
    public void set(String key, Object value, long expire) {
        valueOperations.set(key, toJson(value));
        // 重新设置过期时间为expire，刷新时间
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    /**
     * 获取指定对象数据
     */
    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = valueOperations.get(key);
        // 重新设置过期时间为expire，刷新时间
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value, clazz);
    }

    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    /**
     * 获取String类型数据
     */
    public String get(String key, long expire) {
        String value = valueOperations.get(key);
        // 重新设置过期时间为expire，刷新时间
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    /**
     * 2020-08-14(soldier):获取实体类list集合数据
     */
    public <T> List<T> getList(String key, Class<T> clazz, long expire) {

        String value = valueOperations.get(key);
        // 重新设置过期时间为expire，刷新时间
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : json2List(value, clazz);
    }

    public <T> List<T> getList(String key, Class<T> clazz) {
        return getList(key, clazz, NOT_EXPIRE);
    }


    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 2020-08-14(soldier): redis的管道 pipeline批量 delete
     * 原因：数据大时，服务器redis cup就飙升到98%-99% redis通过key模糊批量查询非常耗性能，原代码如下：
     *      Set<String> keysList = valueOperations.keys(keys);
     *      redisTemplate.delete(keysList);
     */
    public void deleteBatch(String key) {
        // 先通过scan模糊查询（测试表现，数据量大时，scan比keys快）
        List<String> keys = new ArrayList<>();
        this.scan(key, item -> {
            //符合条件的key
            String tmp = new String(item, StandardCharsets.UTF_8);
            keys.add(tmp);
        });

        // 删除keys
        redisTemplate.delete(keys);

        // 再通过pipeline批量删除
        /*redisTemplate.executePipelined(new SessionCallback() {
            //执行流水线
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                //批量处理的内容
                for (String tmp : keys) {
                    operations.delete(tmp);
                }
                return null;
            }
        });*/
    }

    /**
     * scan 实现
     * @param pattern   表达式
     * @param consumer  对迭代到的key进行操作
     */
    public void scan(String pattern, Consumer<byte[]> consumer) {
        redisTemplate.execute((RedisConnection connection) -> {
            try (Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().count(Long.MAX_VALUE).match(pattern).build())) {
                cursor.forEachRemaining(consumer);
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * 自增，当key不存在时，会初始化为1
     */
    public void incr(String key) {
        valueOperations.increment(key);
    }

    /**
     * 自减，当key不存在时，会初始化为1
     */
    public void decr(String key) {
        valueOperations.decrement(key);
    }

    /**
     * 数据转换
     */
    private String toJson(Object object) {
        if (object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String) {
            return String.valueOf(object);
        }
        return gson.toJson(object);
    }

    private <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    private <T> List<T> json2List(String string, Class<T> clazz) {
        List<T> list = new ArrayList<T>();
        try {
            Gson gson = new Gson();
            JsonArray arry = new JsonParser().parse(string).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                list.add(gson.fromJson(jsonElement, clazz));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
