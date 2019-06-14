package com.stackroute.gipherrecommendersystem.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.gipherrecommendersystem.domain.Recommendation;
import com.stackroute.gipherrecommendersystem.service.RecommendationServiceImpl;
import com.stackroute.rabbitmq.domain.MqPayload;

@Component
public class Consumer {

	@Autowired
	private RecommendationServiceImpl recommendationService;

	@RabbitListener(queues = "recommendation_queue")
	public void getRecommendationFromRabbitMq(MqPayload record) {
		Recommendation recommendation = new Recommendation(record.getId(), record.getGif());
		recommendationService.updateRecommendation(recommendation);
	}
}
