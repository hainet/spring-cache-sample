package com.hainet.spring.cache.sample.domain.service;

import ch.qos.logback.classic.gaffer.ConfigurationDelegate;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@CacheConfig(cacheNames = "result")
public class SlowService {

    public String uncacheableOperation() {
        System.out.println(this.getClass().getSimpleName() + "#uncacheableOperation is invoked.");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "OK";
    }

    @Cacheable
    public String cacheableOperation() {
        System.out.println(this.getClass().getSimpleName() + "#cacheableOperation is invoked.");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "OK";
    }

    @CacheEvict
    public String cacheEvictOperation() {
        return "OK";
    }

    @CachePut
    public String cachePutOperation() {
        System.out.println(this.getClass().getSimpleName() + "#cachePutOperation is invoked.");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "OK";
    }

    @Cacheable
    public String cacheableWithArgumentOperation(final String arg) {
        System.out.println(this.getClass().getSimpleName() + "#cacheableWithArgumentOperation is invoked.");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "OK";
    }

    @CachePut
    public String cachePutWithArgumentOperation(final String arg) {
        System.out.println(this.getClass().getSimpleName() + "#cachePutWithArgumentOperation is invoked.");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "OK";
    }

    @Cacheable(condition = "#caching")
    public String conditionalCacheableOperation(final boolean caching) {
        System.out.println(this.getClass().getSimpleName() + "#conditionalCacheableOperation is invoked.");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "OK";
    }

    @CachePut(condition = "#caching")
    public String conditionalCachePutOperation(final boolean caching) {
        System.out.println(this.getClass().getSimpleName() + "#conditionalCachePutOperation is invoked.");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "OK";
    }
}
