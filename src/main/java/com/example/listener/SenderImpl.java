package com.example.listener;

import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class SenderImpl implements Sender {

    private final Logger log = LoggerFactory.getLogger(SenderImpl.class);

    @Override
    @Async
    public void send(String operation, Object entity) {
        Method[] declaredMethods = ReflectionUtils.getDeclaredMethods(entity.getClass());
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.getName().equalsIgnoreCase("getId")) {
                Object id = ReflectionUtils.invokeMethod(declaredMethod, entity);
                log.info("operation - {}, id - {}", operation, id);
            }
        }
    }
}
