package com.peilian.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq 配置
 *
 * @author fable tang
 * @date 2017/10/16
 */
@Configuration
public class MqConfig {
//    public class MqConfig implements RabbitListenerConfigurer {

    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    Queue queueTraceBs(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue("app_logs", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

//    @Bean
//    TopicExchange exchange(RabbitAdmin rabbitAdmin) {
//        TopicExchange topicExchange = new TopicExchange("logstash");
//        rabbitAdmin.declareExchange(topicExchange);
//        return topicExchange;
//    }
    @Bean
    DirectExchange exchange(RabbitAdmin rabbitAdmin) {
    DirectExchange ex = new DirectExchange("logstash");
    rabbitAdmin.declareExchange(ex);
    return ex;
    }

    @Bean
    DirectExchange dExchange(RabbitAdmin rabbitAdmin) {
        DirectExchange ex = new DirectExchange("ms-demo");
        rabbitAdmin.declareExchange(ex);
        return ex;
    }


    /**
     * 配置 queue: demo-hello
     * @return
     */
    @Bean
    public Queue helloQueue() {
        return new Queue("demo-hello");
    }

    @Bean
    public Queue countryQueue() {
        return new Queue("demo-country");
    }
    @Bean
    DirectExchange sdExchange(RabbitAdmin rabbitAdmin) {
        DirectExchange ex = new DirectExchange("ms-student");
        rabbitAdmin.declareExchange(ex);
        return ex;
    }
    @Bean
    public Queue tmpQueue() {
        return new Queue("ms-student2");
    }

    @Bean
    Binding bindingExchangeTmp(Queue tmpQueue, DirectExchange sdExchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(tmpQueue).to(sdExchange).with("register.success");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }
//    @Bean
//    public Queue tmpQueue2() {
//        return new Queue("ms-student2");
//    }
//
//    @Bean
//    Queue queuetemp(RabbitAdmin rabbitAdmin) {
//        Queue queue = new Queue("ms-student2", true);
//        rabbitAdmin.declareQueue(queue);
//        return queue;
//    }
}
