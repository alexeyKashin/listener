package com.example.listener;

import java.util.Collections;
import java.util.Map;
import org.hibernate.jpa.boot.spi.IntegratorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

@Component
public class HibernateConfig implements HibernatePropertiesCustomizer {

    private final HibernateIntegrator hibernateIntegrator;

    @Autowired
    public HibernateConfig(HibernateIntegrator hibernateIntegrator) {
        this.hibernateIntegrator = hibernateIntegrator;
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put("hibernate.integrator_provider",
                                (IntegratorProvider) () -> Collections.singletonList(hibernateIntegrator));
    }
}