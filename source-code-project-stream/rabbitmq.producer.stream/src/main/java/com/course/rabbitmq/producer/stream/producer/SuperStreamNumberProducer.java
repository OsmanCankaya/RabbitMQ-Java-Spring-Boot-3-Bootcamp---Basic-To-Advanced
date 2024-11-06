package com.course.rabbitmq.producer.stream.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;

import com.course.rabbitmq.producer.stream.config.RabbitmqSuperStreamConfig;

//@Service
public class SuperStreamNumberProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	@Qualifier("superStreamNumberTemplate")
	private RabbitStreamTemplate rabbitStreamTemplate;

	public void sendNumbersUsingRabbitTemplate(int start, int end) {
		for (int i = start; i < end; i++) {
			var str = "Number " + i;
			var routingKey = Integer.toString(i % RabbitmqSuperStreamConfig.SUPER_STREAM_NUMBER_PARTITIONS);

			rabbitTemplate.convertAndSend(RabbitmqSuperStreamConfig.SUPER_STREAM_NUMBER_NAME, routingKey, str);
		}

		System.out.printf("Sent super stream (rabbitTemplate) %d to %d", start, (end - 1));
	}

	public void sendNumbersUsingRabbitStreamTemplate(int start, int end) {
		for (int i = start; i < end; i++) {
			var str = "Number " + i;
			var message = rabbitStreamTemplate.messageBuilder().addData(str.getBytes()).properties().messageId(i)
					.messageBuilder().build();

			rabbitStreamTemplate.send(message);
		}

		System.out.printf("Sent super stream (rabbitStreamTemplate) %d to %d", start, (end - 1));
	}

}
