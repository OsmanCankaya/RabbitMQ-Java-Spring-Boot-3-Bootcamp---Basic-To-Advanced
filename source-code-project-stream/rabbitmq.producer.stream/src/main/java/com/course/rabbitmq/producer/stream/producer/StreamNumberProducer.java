package com.course.rabbitmq.producer.stream.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

//@Service
public class StreamNumberProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendNumbers(int start, int end) {
		for (int i = start; i < end; i++) {
			var str = "Number " + i;
			rabbitTemplate.convertAndSend("x.number", null, str);
		}

		System.out.printf("Sent %d to %d", start, (end - 1));
	}

}
