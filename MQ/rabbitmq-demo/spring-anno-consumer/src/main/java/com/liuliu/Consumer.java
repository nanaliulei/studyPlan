package com.liuliu;

import com.liuliu.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.UnsupportedEncodingException;

/**
 * @Author: liulei
 * @Time: 2021/3/12 9:20
 * @Description
 */

public class Consumer {

    public static void main(String[] args) throws UnsupportedEncodingException {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(RabbitConfig.class);
        RabbitTemplate rabbitTemplate = context.getBean(RabbitTemplate.class);
        Message message = rabbitTemplate.receive("que.anno");
        String encoding = message.getMessageProperties().getContentEncoding();
        String s = new String(message.getBody(), encoding);
        System.out.println(s);
        context.close();
    }
}
