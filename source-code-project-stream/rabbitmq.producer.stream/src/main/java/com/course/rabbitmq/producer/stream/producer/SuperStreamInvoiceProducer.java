package com.course.rabbitmq.producer.stream.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;
import org.springframework.stereotype.Service;

import com.course.rabbitmq.producer.stream.entity.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SuperStreamInvoiceProducer {

	@Autowired
	@Qualifier("superStreamInvoiceTemplate")
	private RabbitStreamTemplate rabbitStreamTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	public void sendInvoiceUsingRabbitStreamTemplate(Invoice invoice) throws JsonProcessingException {
		var jsonBytes = objectMapper.writeValueAsBytes(invoice);
		var message = rabbitStreamTemplate.messageBuilder().addData(jsonBytes).properties()
				.messageId(invoice.getInvoiceNumber()).messageBuilder().build();

		rabbitStreamTemplate.send(message);
	}

}
