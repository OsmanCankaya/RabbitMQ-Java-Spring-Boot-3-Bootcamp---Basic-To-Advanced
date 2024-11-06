package com.course.rabbitmq.two.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;
import org.springframework.stereotype.Service;

@Service
public class StreamHelloProducer {

	@Autowired
	@Qualifier("rabbitStreamTemplateHello")
	private RabbitStreamTemplate rabbitStreamTemplate;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendHello(String s) {
		rabbitStreamTemplate.convertAndSend(s);
	}

	public void sendHelloUsingExchange(String s) {
		rabbitTemplate.convertAndSend("x.hello", "rk.hello", s);
	}

}
