package com.liuliu.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Author: liulei
 * @Time: 2021/3/11 8:38
 * @Description
 */

public class Producer {

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("81.70.218.187");
        factory.setPort(5672);
        factory.setUsername("root");
        factory.setPassword("liu123qwe");
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("exchange.pub", BuiltinExchangeType.FANOUT);
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("exchange.pub", "", null, ("hello liuliu" + i).getBytes());
        }
        channel.close();
        connection.close();
    }
}
