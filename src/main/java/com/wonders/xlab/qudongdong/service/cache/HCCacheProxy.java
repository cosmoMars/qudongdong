package com.wonders.xlab.qudongdong.service.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import java.util.Map;

/**
 * 缓存代理类，用于代理具体的缓存实现，方便以后切换缓存实现。
 *
 * @param <K>
 * @param <V>
 * @author xu
 */
public class HCCacheProxy<K, V> implements HCCache<K, V> {
    /**
     * ehCache缓存实现
     */
    private Cache ehCache;

    public HCCacheProxy(Cache cache) {
        this.ehCache = cache;
    }

    @Override
    public void addToCache(K key, V value) {
        Element element = new Element(key, value);
        ehCache.put(element);
    }

    @Override
    public void addToCache(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            Element element = new Element(key, value);
            ehCache.put(element);
        }
    }

    @Override
    public void removeFromCache(K key) {
        ehCache.remove(key);
    }

    @Override
    public V getFromCache(K key) {
        Element element = ehCache.get(key);
        if (element != null) {
            return (V) element.getObjectValue();
        }
        return null;
    }

    /**
     * 如果该key在缓存中存在，返回旧值，否则，返回新值
     * @param key
     * @param value
     * @return
     */
    @Override
    public V putIfAbsent(K key, V value) {
        Element element = new Element(key, value);
        Element oldElement = ehCache.putIfAbsent(element);
        return (V) ehCache.get(key).getObjectValue();
//        return oldElement != null ? (V)oldElement.getObjectValue() : value;
    }

}
