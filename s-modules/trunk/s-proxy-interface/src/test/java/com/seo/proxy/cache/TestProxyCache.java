package com.seo.proxy.cache;

import com.seo.proxy.model.Proxy;
import com.seo.proxy.model.ProxyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class TestProxyCache {
    private final static Logger LOGGER = LoggerFactory.getLogger(TestProxyCache.class);

    private ProxyCache proxyCache;

    @BeforeMethod
    public void init() {
        proxyCache = new ProxyCacheImpl();
    }

    @Test
    public void testEmptyCache() {
        LOGGER.debug("executing testProxyCache()");

        proxyCache.putProxy("key1", new Proxy("host1", 80, ProxyType.HTTP));

        assertNull(proxyCache.getProxy("key1", ProxyType.HTTP));
    }

    @Test
    public void testSimpleCache() {
        LOGGER.debug("executing simple cache test");

        proxyCache.putProxy("key1", new Proxy("host1", 80, ProxyType.HTTP));

        assertEquals(proxyCache.getProxy("key2", ProxyType.HTTP), new Proxy("host1", 80, ProxyType.HTTP));
    }

    @Test
    public void testCache() {
        LOGGER.debug("executing simple cache test");

        proxyCache.putProxy("key1", new Proxy("host1", 80, ProxyType.HTTP));
        proxyCache.putProxy("key2", new Proxy("host2", 80, ProxyType.HTTP));

        assertEquals(proxyCache.getProxy("key1", ProxyType.HTTP), new Proxy("host2", 80, ProxyType.HTTP));
        assertNull(proxyCache.getProxy("key1", ProxyType.HTTP));
    }
}
