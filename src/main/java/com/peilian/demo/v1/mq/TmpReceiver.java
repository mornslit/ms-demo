package com.peilian.demo.v1.mq;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

//@RabbitListener(
//        bindings = @QueueBinding(
//        value = @Queue(value = "ms-student2" , durable = "true") ,
//        exchange = @Exchange(value = "ms-student" , type = "direct" , durable = "true") ,
//        key = "register.success")
//)

//@RabbitListener(
//        bindings = @QueueBinding(
//                value = @Queue(value = "demo-country" , durable = "true") ,
//                exchange = @Exchange(value = "ms-demo" , type = "direct" , durable = "true") ,
//                key = "demo-country")
//)
/**
 * 消费country mq
 *
 * @author fable tang
 * @date 2017/10/16
 */
@Component
public class TmpReceiver {
//    @RabbitHandler
    @RabbitListener(queues = "ms-student2" )
    public void process(Tmp tmp) {
        //do something
        System.out.println("##-------- tmp Receiver : " + tmp);
    }
}
