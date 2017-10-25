package com.peilian.demo.v1.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peilian.demo.v1.entity.Country;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
/**
 * country mq 生产者
 *
 * @author fable tang
 * @date 2017/10/16
 */
public class TmpSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitMessagingTemplate messagingTemplate;


    public void send(Tmp tmp) throws JsonProcessingException {
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
//        this.rabbitTemplate.convertAndSend("ms-student","register.success", new ObjectMapper().writeValueAsString(tmp));
        this.rabbitTemplate.convertAndSend("ms-student","register.success", tmp);
//        messagingTemplate.setMessageConverter(new MappingJackson2MessageConverter());
//        this.messagingTemplate.convertAndSend("ms-student","register.success",tmp);
    }
}