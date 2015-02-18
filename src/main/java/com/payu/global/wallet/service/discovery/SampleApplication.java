package com.payu.global.wallet.service.discovery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.cloud.bus.jackson.SubtypeModule;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.consul.bus.SimpleRemoteEvent;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

/**
 * @author Spencer Gibb
 */
@ComponentScan
@EnableDiscoveryClient
@EnableFeignClients
@RestController
public class SampleApplication implements ApplicationListener<SimpleRemoteEvent> {

    private static final Logger log = LoggerFactory.getLogger(SampleApplication.class);

    public static final String CLIENT_NAME = "testConsulApp";

    @Autowired
    LoadBalancerClient loadBalancer;

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    Environment env;

    @Autowired(required = false)
    RelaxedPropertyResolver resolver;

    @RequestMapping("/me")
    public ServiceInstance me() {
        return discoveryClient.getLocalServiceInstance();
    }

    @RequestMapping("/")
    public ServiceInstance lb() {
        return loadBalancer.choose(CLIENT_NAME);
    }

    @RequestMapping("/instances")
    public List<ServiceInstance> instances() {
        return discoveryClient.getInstances(CLIENT_NAME);
    }

    @RequestMapping("/myenv")
    public String env(@RequestParam("prop") String prop) {
        String property = new RelaxedPropertyResolver(env).getProperty(prop, "Not Found");
        return property;
    }

    @Bean
    public SubtypeModule sampleSubtypeModule() {
        return new SubtypeModule(SimpleRemoteEvent.class);
    }

    @Override
    public void onApplicationEvent(SimpleRemoteEvent event) {
        log.info("Received event: {}", event);
    }

    @Autowired
    HelloClient client;

    @RequestMapping("/helloClient")
    public String helloClient() {
        return client.hello();
    }

    @RequestMapping("/helloServer")
    public String helloServer() {
        ServiceInstance localInstance = discoveryClient.getLocalServiceInstance();
        return "Hello World: "+ localInstance.getServiceId()+":"+localInstance.getHost()+":"+localInstance.getPort();
    }

    @FeignClient("testConsulApp")
    interface HelloClient {
        @RequestMapping(value = "/helloServer", method = GET)
        String hello();
    }
}

