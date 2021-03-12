package com.liuliu.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Author: liulei
 * @Time: 2021/3/11 9:15
 * @Description
 */

public class Consumer {

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:liu123qwe@81.70.218.187:5672/");
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("queue.ex.pub.default", true, false, true, null);
        channel.basicConsume("queue.ex.pub.default", (consumerTag, message) -> {
            System.out.println(new String(message.getBody(), "utf-8"));
        }, consumerTag -> {});

    }
}
