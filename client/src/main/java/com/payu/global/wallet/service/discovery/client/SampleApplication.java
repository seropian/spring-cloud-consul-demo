package com.payu.global.wallet.service.discovery.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Spencer Gibb
 */

@RestController
public class SampleApplication {

    private static final Logger log = LoggerFactory.getLogger(SampleApplication.class);

    @Autowired
    Environment env;

    @Autowired(required = false)
    RelaxedPropertyResolver resolver;

    @RequestMapping("/env")
    public String env(@RequestParam("prop") String prop) {
        String property = new RelaxedPropertyResolver(env).getProperty(prop, "Not Found");
        return property;
    }


}

