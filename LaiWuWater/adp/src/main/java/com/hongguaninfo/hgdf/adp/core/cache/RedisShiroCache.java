package com.hongguaninfo.hgdf.adp.core.cache;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.*;


/**
 * shiro cache的redis实现<br>
 * Created by qiujingde on 2016/10/27.
 * @author qiujingde
 * @since 3.0.0-SNAPSHOT
 */
public class RedisShiroCache <K, V> implements Cache<K, V> {

    /**
     * Private internal log instance.
     */
    private static final Log log = LogFactory.getLog(RedisShiroCache.class);

    private RedisSerializer<Object> serializer;

    private final RedisTemplate<K, V> redisTemplate;
    private String name;
    private long expiration;
    private byte[] rawName;
    private byte[] keysPattern;

    /**
     * 根据RedisTemplate和缓存名称（name）构建redis缓存
     * @param redisTemplate RedisTemplate
     * @param name 缓存名称
     */
    public RedisShiroCache(RedisTemplate<K, V> redisTemplate, String name) {
        this.redisTemplate = redisTemplate;
        this.name = name;
        initialise();
    }

    /**
     * 初始化操作
     */
    private void initialise() {
        serializer = new JdkSerializationRedisSerializer(getClass().getClassLoader());
        rawName = (name + ":").getBytes();
        keysPattern = (name + ":*").getBytes();
    }

    public String getName() {
        return name;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    @Override
    public V get(K key) {
        try {
            if (log.isTraceEnabled()) {
                log.trace("Getting object from cache [" + name + "] for key [" + key + "]");
            }
            if (key == null) {
                return null;
            } else {
                V value = lookup(key);
                if (value == null && log.isTraceEnabled()) {
                    log.trace("Value for [" + key + "] is null.");
                }
                return value;
            }
        } catch (Exception t) {
            throw new CacheException(t);
        }
    }

    @Override
    public V put(final K key, final V value) {
        if (log.isTraceEnabled()) {
            log.trace("Putting object in cache [" + name + "] for key [" + key + "]");
        }
        try {
            V previous = get(key);

            redisTemplate.execute(new RedisCallback<Long>() {
                @Override
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    byte[] rawKey = rawKey(key);
                    byte[] rawValue = serialize(value);
                    connection.set(rawKey, rawValue);
                    if (expiration > 0) {
                        connection.expire(rawKey, expiration);
                    }
                    return 1L;
                }
            });

            return previous;
        } catch (Exception t) {
            throw new CacheException(t);
        }
    }

    @Override
    public V remove(final K key) {
        if (log.isTraceEnabled()) {
            log.trace("Removing object from cache [" + name + "] for key [" + key + "]");
        }
        try {
            V previous = get(key);
            redisTemplate.execute(new RedisCallback<Long>() {
                @Override
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    return connection.del(rawKey(key));
                }
            });
            return previous;
        } catch (Exception t) {
            throw new CacheException(t);
        }
    }

    @Override
    public void clear() {
        if (log.isTraceEnabled()) {
            log.trace("Clearing all objects from cache [" + name + "]");
        }
        try {
            Set<byte[]> keySet = getAllKeys();
            final byte[][] keys = keySet.toArray(new byte[keySet.size()][]);
            redisTemplate.execute(new RedisCallback<String>() {
                @Override
                public String doInRedis(RedisConnection connection) throws DataAccessException {
                    connection.del(keys);
                    return "ok";
                }
            });
        } catch (Exception t) {
            throw new CacheException(t);
        }
    }

    @Override
    public int size() {
        return keys().size();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<K> keys() {
        try {
            Set<byte[]> keySet = getAllKeys();
            if (!CollectionUtils.isEmpty(keySet)) {
                Set<K> keys = new LinkedHashSet<>();
                for (byte[] key : keySet) {
                    keys.add((K) deserialize(key));
                }
                return Collections.unmodifiableSet(keys);
            } else {
                return Collections.emptySet();
            }
        } catch (Exception t) {
            throw new CacheException(t);
        }
    }

    @Override
    public Collection<V> values() {
        try {
            @SuppressWarnings({"unchecked"})
            Set<byte[]> keys = getAllKeys();
            if (!CollectionUtils.isEmpty(keys)) {
                List<V> values = new ArrayList<>(keys.size());
                for (byte[] key : keys) {
                    V value = lookup(key);
                    if (value != null) {
                        values.add(value);
                    }
                }
                return Collections.unmodifiableList(values);
            } else {
                return Collections.emptyList();
            }
        } catch (Exception t) {
            throw new CacheException(t);
        }
    }

    /**
     * 从redis根据key获取value对象
     */
    private V lookup(final K key) {
        byte[] rawKey = rawKey(key);
        return lookup(rawKey);
    }

    @SuppressWarnings("unchecked")
    private V lookup(final byte[] rawKey) {
        return redisTemplate.execute(new RedisCallback<V>() {
            @Override
            public V doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] rawValue = connection.get(rawKey);
                if (rawValue == null) {
                    return null;
                }
                return (V) deserialize(rawValue);
            }
        });

    }

    private Set<byte[]> getAllKeys() {
        return redisTemplate.execute(new RedisCallback<Set<byte[]>>() {
            @Override
            public Set<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.keys(keysPattern);
            }
        });
    }

    private byte[] rawKey(Object key) {
        byte[] keyBytes = serialize(key);
        byte[] rawKey = Arrays.copyOf(rawName, rawName.length + keyBytes.length);
        System.arraycopy(keyBytes, 0, rawKey, rawName.length, keyBytes.length);
        return rawKey;
    }

    private byte[] serialize(Object obj) {
        return serializer.serialize(obj);
    }

    private Object deserialize(byte[] bytes) {
        return serializer.deserialize(bytes);
    }

}
