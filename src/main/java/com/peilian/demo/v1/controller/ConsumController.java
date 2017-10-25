package com.peilian.demo.v1.controller;

import com.peilian.demo.v1.service.feign.ConsumerClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 调用其他微服务的功能
 *
 * @author fable tang
 * @date 2017/10/16
 */
@RestController
@EnableCircuitBreaker
@Log4j2
class ConsumController {
    @Autowired
    ConsumerClient client;

    @RequestMapping(value = "/testFeign", method = RequestMethod.GET)
    public String test(String str) {
        String result = client.hello(str + " by Feign Client ");
        log.debug("++++---/testFeign:" + result);
        return result;
    }
}
