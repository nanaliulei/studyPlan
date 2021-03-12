package com.liuliu.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

/**
 * @Author: liulei
 * @Time: 2021/3/12 19:28
 * @Description
 */
@ComponentScan("com.liuliu.listener")
@Configuration
@EnableRabbit
public class RabbitConfig {

    //connectionFactory
    @Bean
    public ConnectionFactory getConnectoryFactory(){
        URI uri = URI.create("amqp://root:liu123qwe@81.70.218.187:5672/%2f");
        ConnectionFactory factory = new CachingConnectionFactory(uri);
        return factory;
    }
    // rabbitTemplate
    @Bean
    @Autowired
    public RabbitTemplate getRabbitTemplate(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        return rabbitTemplate;
    }
    // rabbitAdmin
    @Bean
    @Autowired
    public RabbitAdmin getRabbitAdmin(ConnectionFactory factory){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(factory);
        return rabbitAdmin;
    }
    // queue
    @Bean
    public Queue getQueue(){
        Queue queue = QueueBuilder.nonDurable("que.anno.listener").build();
        return queue;
    }
    // exchange
    @Bean
    public Exchange getExchange(){
        Exchange exchange = ExchangeBuilder.directExchange("ex.anno").autoDelete().build();
        return exchange;
    }
    // binding
    @Bean
    @Autowired
    public Binding getBinding(Queue queue, Exchange exchange){
        Binding binding = BindingBuilder.bind(queue).to(exchange).with("route.anno.listener").noargs();
        return binding;
    }
    //listener
    @Bean("rabbitListenerContainerFactory")
    @Autowired
    public RabbitListenerContainerFactory getRabbitListenerContainerFactory(ConnectionFactory factory){
        SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();
        containerFactory.setConnectionFactory(factory);
        containerFactory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return containerFactory;
    }
}
