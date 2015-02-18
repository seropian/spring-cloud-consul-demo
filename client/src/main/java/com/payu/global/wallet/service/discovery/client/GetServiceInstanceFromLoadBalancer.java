package com.payu.global.wallet.service.discovery.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dikran.seropian on 2/18/2015.
 */
@RestController
public class GetServiceInstanceFromLoadBalancer {

    public static final String CLIENT_NAME = "testConsulAppService";

    @Autowired
    LoadBalancerClient loadBalancer;

    @RequestMapping("/loadBalancedServiceInstance")
    public ServiceInstance getServiceInstance() {
        return loadBalancer.choose(CLIENT_NAME);
    }
}
