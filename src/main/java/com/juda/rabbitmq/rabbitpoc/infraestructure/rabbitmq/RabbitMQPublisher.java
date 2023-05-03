package com.juda.rabbitmq.rabbitpoc.infraestructure.rabbitmq;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQPublisher {

    private static final String EXCHANGE_NAME = "exchange-poc";
    private static final String QUEUE_NAME = "queue-poc";
    private static final String ROUTING_KEY = "routing-key-poc";

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setExchange(EXCHANGE_NAME);
        rabbitTemplate.setRoutingKey(QUEUE_NAME);
        rabbitTemplate.setRoutingKey(ROUTING_KEY);

        rabbitTemplate.setReturnsCallback((ReturnedMessage message) -> {
            System.out.println(
                    "Message returned: " + message.getMessage()
                        + " with replyCode: " + message.getReplyCode()
                        + " and replyText: " + message.getReplyText()
                        + " to exchange: " + message.getExchange()
                        + " and routingKey: " + message.getRoutingKey())
            ;
            // se pueden tomar medidas adicionales aquí
        });
    }

    public void publish(String message) {
        System.out.println("publishing -> " + message);
        rabbitTemplate.convertAndSend(message);
    }

}

