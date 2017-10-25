package com.peilian.demo.config;

import com.peilian.comm.ms.trace.filter.TraceBsFilter;
import com.peilian.comm.ms.trace.interceptor.TraceBsInterceptor;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 业务链追踪 配置
 *
 * @author fable tang
 * @date 2017/10/16
 */
@Configuration
public class MyWebMvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;
    @Value("${spring.application.name}")
    private String serviceName;

    @Bean
    public FilterRegistrationBean traceBsFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new TraceBsFilter(serviceName, rabbitMessagingTemplate));
        //需要拦截的url, 只支持 * ,不支持 ** ?
        registration.addUrlPatterns("/v1/*");
        registration.addUrlPatterns("/v2/*");
        registration.setOrder(900);
        return registration;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //需要拦截的url, 支持 ? * **
        registry.addInterceptor(new TraceBsInterceptor()).addPathPatterns("/v?/**");
        super.addInterceptors(registry);
    }
}