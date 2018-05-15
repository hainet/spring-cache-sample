package com.hainet.spring.cache.sample.domain.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SlowServiceTest {

    @Autowired
    private SlowService service;

    @Test
    public void uncacheableOperationTest() {
        final long start = System.currentTimeMillis();

        assertThat(this.service.uncacheableOperation(), is("OK"));
        assertThat(this.service.uncacheableOperation(), is("OK"));

        assertThat(System.currentTimeMillis() - start, is(greaterThan(5000L)));
    }

    @Test
    public void cacheableOperationTest() {
        final long start = System.currentTimeMillis();

        assertThat(this.service.cacheableOperation(), is("OK"));
        assertThat(this.service.cacheableOperation(), is("OK"));

        assertThat(System.currentTimeMillis() - start, is(lessThan(5000L)));
    }

    @Test
    public void cacheEvictOperationTest() {
        final long start = System.currentTimeMillis();

        // Cache.
        assertThat(this.service.cacheableOperation(), is("OK"));
        // Use cache.
        assertThat(this.service.cacheableOperation(), is("OK"));
        // Evict cache.
        assertThat(this.service.cacheEvictOperation(), is("OK"));
        // Cache again.
        assertThat(this.service.cacheableOperation(), is("OK"));

        final long end = System.currentTimeMillis() - start;
        assertThat(end, is(greaterThan(5000L)));
        assertThat(end, is(lessThan(7000L)));
    }

    @Test
    public void cachePutOperationTest() {
        final long start = System.currentTimeMillis();

        // Cache.
        assertThat(this.service.cacheableOperation(), is("OK"));
        // Use cache.
        assertThat(this.service.cacheableOperation(), is("OK"));
        // Put cache.
        assertThat(this.service.cachePutOperation(), is("OK"));
        // Use cache.
        assertThat(this.service.cacheableOperation(), is("OK"));

        final long end = System.currentTimeMillis() - start;
        assertThat(end, is(greaterThan(5000L)));
        assertThat(end, is(lessThan(7000L)));
    }

    @Test
    public void withArgumentOperationTest() {
        final long start = System.currentTimeMillis();
        final String arg = "Argument";

        // Cache with argument.
        assertThat(this.service.cachePutWithArgumentOperation(arg), is("OK"));
        // Can't use cache.
        assertThat(this.service.cacheableOperation(), is("OK"));
        // Use cache
        assertThat(this.service.cacheableWithArgumentOperation(arg), is("OK"));

        final long end = System.currentTimeMillis() - start;
        assertThat(end, is(greaterThan(5000L)));
        assertThat(end, is(lessThan(7000L)));
    }
}
