package com.peilian.demo.v1.mq;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(
        bindings = @QueueBinding(
        value = @Queue(value = "demo-country" , durable = "true") ,
        exchange = @Exchange(value = "ms-demo" , type = "direct" , durable = "true") ,
        key = "demo-country")
)
/**
 * 消费country mq
 *
 * @author fable tang
 * @date 2017/10/16
 */
public class CountryReceiver {
    @RabbitHandler
    public void process(String countryStr) {
        //do something
        System.out.println("## Receiver : " + countryStr);
    }
}
