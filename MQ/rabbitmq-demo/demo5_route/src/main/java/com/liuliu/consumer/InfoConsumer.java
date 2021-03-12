package com.liuliu.consumer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Author: liulei
 * @Time: 2021/3/11 19:11
 * @Description
 */

public class InfoConsumer {

    public static void main(String[] args) throws Exception{
        final ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:liu123qwe@81.70.218.187:5672/");
        factory.setVirtualHost("/");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare("queue.route.info", false, false, false, null);
        channel.exchangeDeclare("ex.route", BuiltinExchangeType.DIRECT, false, false, null);
        channel.queueBind("queue.route.info", "ex.route", "info");
        channel.basicConsume("queue.route.info", (consumerTag, message) -> {
            System.out.println(new String(message.getBody(), "utf-8"));
        }, consumerTag -> {});
    }
}
