package com.example.consumer_rabbitmqapp.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String ACTION_QUEUE_ALL = "action_queue_all";

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

}
