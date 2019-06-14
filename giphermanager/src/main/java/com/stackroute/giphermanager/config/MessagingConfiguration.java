package com.stackroute.giphermanager.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MessagingConfiguration {
	
	private String exchangeName = "user_exchange";
	private String recommendationQueue = "recommendation_queue";
	private String recommendationRoutingKey = "recommendation_routing";
	
	@Bean
	DirectExchange directExchange() {
		return new DirectExchange(exchangeName);
	}
	
	@Bean
	Queue queueRecommendation() {
		return new Queue(recommendationQueue, false);
	}
	
	@Bean
	Binding bindingRecommendation(Queue queueRecommendation, DirectExchange exchange) {
		return BindingBuilder.bind(queueRecommendation()).to(exchange).with(recommendationRoutingKey);
	}
	
	@Bean
	public Jackson2JsonMessageConverter producerJackson2JsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2JsonMessageConverter());
		return rabbitTemplate;
	}
}
