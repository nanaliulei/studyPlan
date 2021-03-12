package com.liuliu;

import com.liuliu.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.awt.*;
import java.io.UnsupportedEncodingException;

/**
 * @Author: liulei
 * @Time: 2021/3/12 8:46
 * @Description
 */

public class Producer {

    public static void main(String[] args) throws UnsupportedEncodingException {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(RabbitConfig.class);
        RabbitTemplate rabbitTemplate = context.getBean(RabbitTemplate.class);
        MessageProperties messageProperties = MessagePropertiesBuilder.newInstance().setContentEncoding("gbk").build();
        Message message = MessageBuilder.withBody("你好，世界".getBytes("gbk")).andProperties(messageProperties).build();
        rabbitTemplate.send("ex.anno", "route.anno.listener", message);
        context.close();
    }
}
