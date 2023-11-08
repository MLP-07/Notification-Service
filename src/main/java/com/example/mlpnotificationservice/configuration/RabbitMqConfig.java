package com.example.mlpnotificationservice.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMqConfig {

    @Value("${rabbitmq.queue.sms.name}")
    String smsQueueName;

    @Value("${rabbitmq.queue.email.name}")
    String emailQueueName;

    @Value("${rabbitmq.exchange.sms.name}")
    private String smsExchange;

    @Value("${rabbitmq.exchange.email.name}")
    private String emailExchange;

    @Value("${rabbitmq.queue.sms.routing.key}")
    private String smsQueueRoutingKey;

    @Value("${rabbitmq.queue.email.routing.key}")
    private String emailQueueRoutingKey;

    @Bean
    public CachingConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public RabbitAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public Queue smsQueue() {
        return new Queue(smsQueueName);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue(emailQueueName);
    }

    @Bean
    public DirectExchange smsExchange() {
        return new DirectExchange(smsExchange);
    }

    @Bean
    public DirectExchange emailExchange() {
        return new DirectExchange(emailExchange);
    }

    @Bean
    public Binding smsBinding() {
        return BindingBuilder
                .bind(smsQueue())
                .to(smsExchange())
                .with(smsQueueRoutingKey);
    }

    @Bean
    public Binding emailBinding() {
        return BindingBuilder
                .bind(emailQueue())
                .to(emailExchange())
                .with(emailQueueRoutingKey);
    }

    @Bean
    public MessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory, MessageConverter jackson2JsonMessageConverter) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
        return rabbitTemplate;
    }
}
