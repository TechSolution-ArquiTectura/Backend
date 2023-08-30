package com.upc.TuCine.security.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.upc.TuCine.security.domain.service.GenderService;
import com.upc.TuCine.security.domain.service.TypeUserService;

import java.sql.Timestamp;

@Component
public class DatabaseSeedingConfig {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseSeedingConfig.class);

    @Autowired
    private TypeUserService typeUserService;

    @Autowired
    private GenderService genderService;

    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        String name = event.getApplicationContext().getId();
        logger.info("Starting Database Seeding Process for {} at {}", name, new Timestamp(System.currentTimeMillis()));
        typeUserService.seed();
        genderService.seed();
        logger.info("Finished Database Seeding Process for {} at {}", name, new Timestamp(System.currentTimeMillis()));
    }
}
