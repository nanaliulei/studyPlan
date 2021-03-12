package com.liuliu.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Author: liulei
 * @Time: 2021/3/11 9:10
 * @Description
 */

public class Producer {

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:liu123qwe@81.70.218.187:5672/");
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("queue.ex.pub.defalut", true, false, true, null);
        channel.basicPublish("", "queue.ex.pub.default", null, "hello junjun".getBytes());
        channel.close();
        connection.close();
    }
}
