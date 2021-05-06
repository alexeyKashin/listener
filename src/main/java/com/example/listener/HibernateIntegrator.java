package com.example.listener;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateIntegrator implements Integrator {

    private final EntityEventListener entityEventListener;
    @Autowired
    public HibernateIntegrator(EntityEventListener entityEventListener) {
        this.entityEventListener = entityEventListener;
    }

    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory,
                          SessionFactoryServiceRegistry serviceRegistry) {

        EventListenerRegistry registry = serviceRegistry.getService(EventListenerRegistry.class);
        registry.appendListeners(EventType.POST_INSERT, entityEventListener);
        registry.appendListeners(EventType.POST_UPDATE, entityEventListener);
        registry.appendListeners(EventType.POST_DELETE, entityEventListener);
    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        // do nothing
    }
}
