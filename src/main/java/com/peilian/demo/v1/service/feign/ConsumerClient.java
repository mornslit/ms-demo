package com.peilian.demo.v1.service.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-auth", fallback = FeignClienHystrix.class)
/**
 * 使用feign 调用ms-auth的功能,rpc调用
 *
 * @author fabletang
 * @date 2017/10/1
 */
public interface ConsumerClient {

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    /**
     * 简单的hello controller
     * @param name 人名
     * @return string 仅供测试，应该返回json
     */
    String hello(@RequestParam("name") String name);

}
