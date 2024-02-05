package com.nowon.bul.chatbot;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

//spring boot의 자동구성을 사용하여 ConnectionFactory, RabbitTemplate 생성
@RequiredArgsConstructor
@Configuration
@EnableRabbit
public class RabbitMQConfig {
	
	private final ConnectionFactory connectionFactory;
	
	@Value("${spring.rabbitmq.template.default-receive-queue}")
	private String queue;
	@Value("${spring.rabbitmq.template.exchange}")
	private String exchange;
	@Value("${spring.rabbitmq.template.routing-key}")
	private String routingKey;
	
	
	@Bean
	Queue queue() {
		return new Queue(queue, false);
	}
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(exchange);
	}
	
	@Bean
	Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
	}
	
	/*
	 
	//rabbitMQ 메세지 리스너 컨테이너 팩토리를 정의
	@Bean
    SimpleRabbitListenerContainerFactory myFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        //ConnectionFactory connectionFactory = getCustomConnectionFactory();
        configurer.configure(factory, connectionFactory);
        //factory.setMessageConverter(new MyMessageConverter());
        return factory;
    }
    */
	
	//RabbitMq 메세지 리스너 컨테이너를 정의
	@Bean
    SimpleMessageListenerContainer container(Receiver receiver) {
		SimpleMessageListenerContainer container=new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queue);
		container.setMessageListener(messageListenerAdapter(receiver));
		return container;
	}
	
	//메세지 리스너 어댑터를 정의
	@Bean
	MessageListenerAdapter messageListenerAdapter(Receiver receiver) {
		MessageListenerAdapter messageListenerAdapter=new MessageListenerAdapter(receiver, "receiveMessage");
		messageListenerAdapter.setMessageConverter(messageConverter());
		return messageListenerAdapter;
	}
	
	@Bean
	MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

}
