package com.seo.core.concurrency.limit;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

@Aspect
public class ProxyUsageAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProxyUsageAspect.class);

    private final static long MIN_FETCH_INTERVAL = 30 * 1000;

    private static Date lastExecutionDate = null;

    @Pointcut("execution(* com.seo.proxy.service.InsorgProxyService.getProxy(..))")
    private void insorgProxyFetching() {}

    @Before("com.seo.core.concurrency.limit.ProxyUsageAspect.insorgProxyFetching()")
    public synchronized void doLimitCheck() {
        LOGGER.debug("going to fetch insorg proxy");

        if(lastExecutionDate == null) {
            LOGGER.debug("executing for first time");
        } else {
            long dateDiff = new Date().getTime() - lastExecutionDate.getTime();

            if(dateDiff < MIN_FETCH_INTERVAL) {
                long sleepTime = MIN_FETCH_INTERVAL - dateDiff;
                LOGGER.debug("sleeping for {} ms", sleepTime);

                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    LOGGER.error("interrupt exception: {} {}", e.getMessage(), e.getStackTrace());
                }
            } else {
                //do nothing
            }
        }

        lastExecutionDate = new Date();
    }
}
