package com.course.rabbitmq.two.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class StreamHelloConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(StreamHelloConsumer.class);

	@RabbitListener(queues = "s.hello")
	public void listenHello(String message) {
		LOG.info("Consuming from stream : {}", message);
	}

}
