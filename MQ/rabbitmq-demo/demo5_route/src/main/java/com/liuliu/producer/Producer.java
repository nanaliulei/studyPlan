package com.liuliu.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Random;
import java.util.logging.Logger;

/**
 * @Author: liulei
 * @Time: 2021/3/11 19:02
 * @Description
 */

public class Producer {

    private static final String[] LOG_LEVEL = {"error", "warn", "info"};

    public static void main(String[] args) throws Exception{
        final ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:liu123qwe@81.70.218.187:5672/");
        factory.setVirtualHost("/");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();
        Random random = new Random();
        channel.exchangeDeclare("ex.route", BuiltinExchangeType.DIRECT, false, false, null);
        for (int i = 0; i < 100; i++) {
            String routingKey = LOG_LEVEL[random.nextInt(10) % LOG_LEVEL.length];
            String message = "[" + routingKey + "]: seqno " + i;
            channel.basicPublish("ex.route", routingKey, null, message.getBytes());
        }
        channel.close();
        connection.close();
    }
}
