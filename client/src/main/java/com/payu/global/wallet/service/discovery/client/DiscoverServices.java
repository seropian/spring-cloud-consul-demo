package com.payu.global.wallet.service.discovery.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.consul.client.CatalogClient;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryClient;
import org.springframework.cloud.consul.model.ServiceNode;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by dikran.seropian on 2/18/2015.
 */
@RestController
public class DiscoverServices {

    public static final String CLIENT_NAME = "testConsulAppService";

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    CatalogClient catalogClient;

    @Bean
    public DiscoveryClient consulDiscoveryClient() {
        return new ConsulDiscoveryClient();
    }

    @RequestMapping("/instances")
    public List<ServiceInstance> instances() {
        return discoveryClient.getInstances(CLIENT_NAME);
    }

    @RequestMapping("/services")
    public List<String> services() {
        return discoveryClient.getServices();
    }



}
