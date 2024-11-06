package com.course.rabbitmq.producer.stream.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;

import com.course.rabbitmq.producer.stream.entity.Invoice;

//@Service
public class StreamInvoiceProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	@Qualifier("streamInvoiceTemplate")
	private RabbitStreamTemplate rabbitStreamTemplate;

	public void sendInvoiceUsingRabbitTemplate(Invoice invoice) {
		rabbitTemplate.convertAndSend("x.invoice", "", invoice);
	}

	public void sendInvoiceUsingRabbitStreamTemplate(Invoice invoice) {
		rabbitStreamTemplate.convertAndSend(invoice);
	}

}
