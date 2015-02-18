package com.payu.global.wallet.service.discovery.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.SubtypeModule;
import org.springframework.cloud.consul.bus.SimpleRemoteEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

/**
 * Created by dikran.seropian on 2/18/2015.
 */
@SpringBootApplication
public class Run implements ApplicationListener<SimpleRemoteEvent> {
    private static final Logger log = LoggerFactory.getLogger(Run.class);

    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }

    @Bean
    public SubtypeModule sampleSubtypeModule() {
        return new SubtypeModule(SimpleRemoteEvent.class);
    }

    @Override
    public void onApplicationEvent(SimpleRemoteEvent event) {
        log.info("Received event: {}", event);
    }
}