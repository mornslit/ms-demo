package com.peilian.demo.v1.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.peilian.comm.exceptionhandling.BsError;
import com.peilian.comm.exceptionhandling.BsException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * controller
 *
 * @author fable tang
 * @date 2017/10/16
 */
@RestController //表明是返回 json格式
@RequestMapping("/v1") //全局url前缀
@Log4j2  //引用log组件,省略写代码
@EnableCircuitBreaker
class JavaController {

    //自动组装bean,并且 new 实例
    @Autowired
    private HttpServletRequest httpRequest;

    /**
     * sdsfds
     * @return String
     */
    @RequestMapping(value = {"/hello"}, method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "helloError")
    public String hello() {
        String result = "" + new Date();
        result += "\nHello Java";
        log.info("+++---/hello:" + result);
        return result;
    }

    /**
     * post请求
     * @return string demo
     */
    @PostMapping("/es")
    public String es() {
        String result = "" + new Date();
        result += "\n log to es";
        log.info("+++---/es:" + result);
        return result;
    }

    @GetMapping("/bird")
    public String bird2(@RequestParam("id") Long id
    ) {
        String result = "" + new Date();
        result += "\n get bird by id=" + id;
        log.info("+++---/bird:" + result);
        return result;
    }

    @GetMapping("/bsErr")
    public String bird3(
    ) throws BsException {
        // do something bs 具体业务逻辑
        throw new BsException(this.getClass(), new BsError("120016", " 业务不允许XXX操作"));
    }

    public String helloError() {
        String result = "hello v1," + "-- sorry,error!!!";
        return result;
    }
}
