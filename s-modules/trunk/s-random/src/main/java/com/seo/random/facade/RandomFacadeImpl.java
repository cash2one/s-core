package com.seo.random.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class RandomFacadeImpl implements RandomFacade{
private final static Logger LOGGER = LoggerFactory.getLogger(RandomFacadeImpl.class);    
    private final static Random RANDOM = new Random();

    @Override
    public int getInteger(Integer number) {
        return RANDOM.nextInt(number);
    }

    @Override
    public int getIntFromRange(int start, int end) {
        if(start > end) {
            LOGGER.error("start > end: start = {}, end = {}", start, end);

            throw new IllegalArgumentException("start > end");
        }

        int diff = end - start;
        int generatedValue = RANDOM.nextInt(diff);

        int resultValue = start + generatedValue;

        LOGGER.debug("generated value {} from range from {} to {}", new Object[]{resultValue, start, end});

        return resultValue;
    }
}
