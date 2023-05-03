package com.juda.rabbitmq.rabbitpoc.infraestructure.rabbitmq;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQInitializer {

    private static final String QUEUE_NAME = "queue-poc";
    private static final String EXCHANGE_NAME = "exchange-poc";
    private static final String ROUTING_KEY = "routing-key-poc";
    private final RabbitAdmin rabbitAdmin;

    @Autowired
    public RabbitMQInitializer(RabbitAdmin rabbitAdmin) {
        this.rabbitAdmin = rabbitAdmin;
    }

    @PostConstruct
    public void initialize() {
        //Creating queues
        Queue queue = new Queue(QUEUE_NAME);
        rabbitAdmin.declareQueue(queue);

        //Creating exchanges
        DirectExchange exchange = new DirectExchange(EXCHANGE_NAME);
        rabbitAdmin.declareExchange(exchange);

        //Binding exchange to the queue
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY));
    }

}

