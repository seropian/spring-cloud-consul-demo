package com.payu.global.wallet.service.discovery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dikran.seropian on 2/18/2015.
 */
@EnableDiscoveryClient
@RestController
public class RegisterService {

    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping("/helloServer")
    public String hello() {
        ServiceInstance localInstance = discoveryClient.getLocalServiceInstance();
        return "Hello World: " + localInstance.getServiceId() + ":" + localInstance.getHost() + ":" + localInstance.getPort();
    }
}
