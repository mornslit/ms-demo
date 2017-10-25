package com.peilian.demo.config;

import com.peilian.comm.exceptionhandling.RestExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 全局异常拦截
 *
 * @author fable tang
 * @date 2017/10/16
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class MyRestExceptionHandler extends RestExceptionHandler {
}
