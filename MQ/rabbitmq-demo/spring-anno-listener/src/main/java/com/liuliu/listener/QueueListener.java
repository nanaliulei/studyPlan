package com.liuliu.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @Author: liulei
 * @Time: 2021/3/12 19:40
 * @Description
 */

@Component
public class QueueListener {

    @RabbitListener(queues = "que.anno.listener")
    public void onMessage(Message message) throws UnsupportedEncodingException {
        System.out.println("消息队列推送消息：" + new String(message.getBody(), message.getMessageProperties().getContentEncoding()));
    }
}
