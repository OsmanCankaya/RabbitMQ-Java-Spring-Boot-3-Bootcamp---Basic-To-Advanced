package com.course.rabbitmq.producer.producer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.rabbitmq.producer.entity.ReportRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ReportRequestProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	public void sendMessage(ReportRequest reportRequest) throws JsonProcessingException {
		var messageProperties = new MessageProperties();
		var delayInMillis = 0l;

		if (reportRequest.isLarge()) {
			delayInMillis = 2 * 60 * 1000l;
		}

		messageProperties.setHeader("x-delay", Long.toString(delayInMillis));

		var json = objectMapper.writeValueAsString(reportRequest);
		var message = new Message(json.getBytes(), messageProperties);

		rabbitTemplate.send("x.delayed", "delayThis", message);
	}

}
