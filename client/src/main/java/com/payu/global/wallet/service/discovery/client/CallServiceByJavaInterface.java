package com.payu.global.wallet.service.discovery.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by dikran.seropian on 2/18/2015.
 */
@EnableFeignClients
@RestController
public class CallServiceByJavaInterface {

    @Autowired
    HelloClient client;

    @RequestMapping("/helloClient")
    public String helloClient() {
        return String.format("Server response: %s}", client.hello());
    }


    @FeignClient("testConsulAppService")
    interface HelloClient {
        @RequestMapping(value = "/helloServer", method = GET)
        String hello();
    }



}
