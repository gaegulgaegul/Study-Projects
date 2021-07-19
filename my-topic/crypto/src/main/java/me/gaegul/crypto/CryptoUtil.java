package me.gaegul.crypto;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class CryptoUtil {

    private <T> T invokeAnnotation(T instance) throws IllegalAccessException {
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            Crypto annotation = field.getAnnotation(Crypto.class);
            if (annotation != null && field.getType() == String.class) {
                field.setAccessible(true);
                String plain = (String) field.get(instance);
                field.set(instance, annotation.type().encrypt(plain));
            }
        }
        return instance;
    }

    public <T> T encrypt(T instance) throws IllegalAccessException {
        return invokeAnnotation(instance);
    }

}
