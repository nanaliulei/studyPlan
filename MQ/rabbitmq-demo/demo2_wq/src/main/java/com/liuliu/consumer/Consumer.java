package com.liuliu.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author: liulei
 * @Time: 2021/3/9 8:50
 * @Description
 */

public class Consumer {

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
        channel.basicConsume("queue.wq", new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery message) throws IOException {
                System.out.println("推送过来的消息：" + new String(message.getBody(), "utf-8"));
            }
        }, new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {

            }
        });
    }
}
