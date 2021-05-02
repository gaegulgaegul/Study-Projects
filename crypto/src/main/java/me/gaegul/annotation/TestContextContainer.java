package me.gaegul.annotation;

import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@Component
public class TestContextContainer {

    public TestContextContainer() {
    }

    private <T> T invokeAnnotation(T instance) throws IllegalAccessException {
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            TestAnnotation annotation = field.getAnnotation(TestAnnotation.class);
            if (annotation != null && field.getType() == String.class) {
                field.setAccessible(true);
                field.set(instance, annotation.value());
            }
        }
        return instance;
    }

    public <T> T get(Class clazz) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Constructor constructor = clazz.getConstructor(null);
        T instance = (T) constructor.newInstance();
        instance = invokeAnnotation(instance);
        return instance;
    }
}
