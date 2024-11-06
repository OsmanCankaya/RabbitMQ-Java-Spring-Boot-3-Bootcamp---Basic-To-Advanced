package com.course.rabbitmq.two.producer;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.rabbitmq.two.entity.DummyMessage;

import jakarta.annotation.PostConstruct;

@Service
public class ReliableProducer {

	private static final Logger LOG = LoggerFactory.getLogger(ReliableProducer.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@PostConstruct
	private void registerCallback() {

		rabbitTemplate.setConfirmCallback((correlation, ack, reason) -> {
			if (correlation == null) {
				return;
			}

			if (ack) {
				LOG.info("Message with correlation {} is published", correlation.getId());
			} else {
				LOG.warn("Invalid exchange. Message with correlation {} is NOT published", correlation.getId());
			}
		});

		rabbitTemplate.setReturnsCallback(returned -> {
			LOG.info("Return callback");

			if (returned.getReplyText() != null && returned.getReplyText().equalsIgnoreCase("NO_ROUTE")) {
				var id = returned.getMessage().getMessageProperties().getHeader("spring_returned_message_correlation")
						.toString();

				LOG.warn("Invalid routing key for id : {}", id);
			}
		});
	}

	public void sendDummyWithInvalidRoutingKey(DummyMessage message) {
		var correlationData = new CorrelationData(UUID.randomUUID().toString());

		rabbitTemplate.convertAndSend("x.dummy", "invalidRoutingKey", message, correlationData);
	}

	public void sendDummyToInvalidExchange(DummyMessage message) {
		var correlationData = new CorrelationData(UUID.randomUUID().toString());

		rabbitTemplate.convertAndSend("invalidExchange", "", message, correlationData);
	}

}
