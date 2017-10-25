package com.peilian.demo.v1.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peilian.demo.v1.entity.Country;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
/**
 * country mq 生产者
 *
 * @author fable tang
 * @date 2017/10/16
 */
public class CountrySender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        this.rabbitTemplate.convertAndSend("demo-hello", context);
    }

    public void send(Country country) throws JsonProcessingException {
        this.rabbitTemplate.convertAndSend("ms-demo","demo-country", new ObjectMapper().writeValueAsString(country));
    }
}