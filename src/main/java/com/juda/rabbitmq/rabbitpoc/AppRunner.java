package com.juda.rabbitmq.rabbitpoc;

import com.juda.rabbitmq.rabbitpoc.infraestructure.rabbitmq.RabbitMQPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {
    @Autowired
    private RabbitMQPublisher rabbitMQPublisher;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Hello RabbitMQ");
        rabbitMQPublisher.publish("Hello, RabbitMQ!");
    }
}
