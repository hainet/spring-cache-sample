package com.hainet.spring.cache.sample.domain.service;

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
}
