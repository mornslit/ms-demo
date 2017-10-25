package com.peilian.demo.v1.service.feign;

import org.springframework.stereotype.Component;

@Component //能被spring管理，可以 @Autowired
/**
 * 使用feign 调用ms-auth的功能,rpc调用
 *
 * @author fabletang
 * @date 2017/10/1
 */
public class FeignClienHystrix implements ConsumerClient {
    @Override
    public String hello(String name) {
        return "FeignClient Hystrix fallback !!! " + name;
    }
}
