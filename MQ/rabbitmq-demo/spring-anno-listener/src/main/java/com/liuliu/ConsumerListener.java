package com.liuliu;

import com.liuliu.config.RabbitConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: liulei
 * @Time: 2021/3/12 19:27
 * @Description
 */

public class ConsumerListener {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(RabbitConfig.class);
    }
}
