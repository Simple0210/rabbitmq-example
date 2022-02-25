package com.example.producerrabbitmqapp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String ACTION_ROUTING_KEY_A = "routing.green";
    public static final String ACTION_ROUTING_KEY_B = "routing.black";
    public static final String ACTION_EXCHANGE = "topic_exchange";
    public static final String ACTION_QUEUE_A = "action_queue_a";
    public static final String ACTION_QUEUE_B = "action_queue_b";
    public static final String ACTION_QUEUE_ALL = "action_queue_all";
    public static final String ROUTING = "routing.*";

    @Bean
    public Queue queueA() {
        return new Queue(ACTION_QUEUE_A, false);
    }

    @Bean
    public Queue queueB() {
        return new Queue(ACTION_QUEUE_B, false);
    }

    @Bean
    public Queue queueAll() {
        return new Queue(ACTION_QUEUE_ALL, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(ACTION_EXCHANGE);
    }

    @Bean
    public Binding bindingA(@Qualifier(value = "queueA") Queue queueA, TopicExchange exchange) {
        return BindingBuilder.bind(queueA)
                .to(exchange)
                .with(ACTION_ROUTING_KEY_A);
    }

    @Bean
    public Binding bindingB(@Qualifier(value = "queueB") Queue queueB, TopicExchange exchange) {
        return BindingBuilder.bind(queueB)
                .to(exchange)
                .with(ACTION_ROUTING_KEY_B);
    }

    @Bean
    public Binding bindingAll(@Qualifier(value = "queueAll") Queue queueAll, TopicExchange exchange) {
        return BindingBuilder.bind(queueAll)
                .to(exchange)
                .with(ROUTING);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
