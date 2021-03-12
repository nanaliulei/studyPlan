package com.liuliu.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Random;

/**
 * @Author: liulei
 * @Time: 2021/3/11 19:21
 * @Description
 */

public class Producer {

    private static final String[] LOG_LEVEL = {"error", "warn", "info"};
    private static final String[] LOG_AREA = {"beijing", "shanghai", "guagnzhou"};
    private static final String[] LOG_BIZ = {"online", "batch"};

    public static void main(String[] args) throws Exception{
        final ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:liu123qwe@81.70.218.187:5672/");
        factory.setVirtualHost("/");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();
        Random random = new Random();
        channel.exchangeDeclare("ex.topic", BuiltinExchangeType.TOPIC);
        for (int i = 0; i < 100; i++) {
            String routingKey, message, level, area, biz;
            level = LOG_LEVEL[random.nextInt(10) % LOG_LEVEL.length];
            area = LOG_AREA[random.nextInt(10) % LOG_AREA.length];
            biz = LOG_BIZ[random.nextInt(10) % LOG_BIZ.length];
            routingKey = level + "." + area + "." + biz;
            message = "[" + level + "]: 来自" + area + " 的 " + biz + " 的务器发来消息" + i;
            channel.basicPublish("ex.topic", routingKey, null, message.getBytes());
        }

        channel.close();
        connection.close();
    }
}
