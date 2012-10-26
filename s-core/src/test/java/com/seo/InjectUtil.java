package com.seo;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class InjectUtil {
    public static void injectMock(Object target, String fieldName, Object object) {
        Field field = ReflectionUtils.findField(target.getClass(), fieldName);
        field.setAccessible(true);
        ReflectionUtils.setField(field, target, object);
    }
}
