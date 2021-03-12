package com.liuliu.producer;

import com.rabbitmq.client.*;

/**
 * @Author: liulei
 * @Time: 2021/3/9 8:40
 * @Description
 */

public class Producer {

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("81.70.218.187");
        factory.setPort(5672);
        factory.setUsername("root");
        factory.setPassword("liu123qwe");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("queue.wq", true, false, true, null);
        channel.exchangeDeclare("exchange.wq", BuiltinExchangeType.DIRECT, true);
        channel.queueBind("queue.wq", "exchange.wq", "rk.wq");
        for (int i = 0; i < 20; i++) {
            channel.basicPublish("exchange.wq", "rk.wq", null, ("hello " + i).getBytes());
        }
        channel.close();
        connection.close();
    }
}
