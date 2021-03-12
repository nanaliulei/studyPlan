package com.liuliu.consumer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Author: liulei
 * @Time: 2021/3/11 8:43
 * @Description
 */

public class ConsumerThree {

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:liu123qwe@81.70.218.187:5672/");
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String queueName = channel.queueDeclare().getQueue();
        channel.exchangeDeclare("exchange.pub", BuiltinExchangeType.FANOUT);
        channel.queueBind(queueName, "exchange.pub", "");
        channel.basicConsume(queueName, (consumerTag, message) -> {
            System.out.println("consumer3:   " + new String(message.getBody(), "utf-8"));
        }, consumerTag -> {});
    }
}
