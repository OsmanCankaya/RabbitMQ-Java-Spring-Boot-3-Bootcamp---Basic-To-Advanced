package com.course.rabbitmq.consumer.stream.consumer;

import java.io.IOException;

import org.apache.qpid.proton.amqp.messaging.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.rabbitmq.consumer.stream.entity.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.stream.Message;
import com.rabbitmq.stream.MessageHandler.Context;

@Service
public class InvoiceConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(InvoiceConsumer.class);

	@Autowired
	private ObjectMapper objectMapper;

//	@RabbitListener(queues = RabbitmqStreamJsonConfig.STREAM_INVOICE)
	public void listenDefault(Invoice message) {
		LOG.info("listenDefault : {}", message);
	}

//	@RabbitListener(queues = RabbitmqStreamJsonConfig.STREAM_INVOICE, containerFactory = "invoiceContainerFactoryOne")
	public void listenWithContainerFactoryOne(String message) throws JsonMappingException, JsonProcessingException {
		LOG.info("listenWithContainerFactoryOne receive JSON string : {}", message);

		var invoice = objectMapper.readValue(message, Invoice.class);
		LOG.info("listenWithContainerFactoryOne : {}", invoice);
	}

//	@RabbitListener(queues = RabbitmqStreamJsonConfig.STREAM_INVOICE, containerFactory = "invoiceContainerFactoryTwo")
	public void listenWithContainerFactoryTwo(Message message, Context context) throws IOException {
		var data = (Data) message.getBody();

		var invoice = objectMapper.readValue(data.getValue().getArray(), Invoice.class);
		LOG.info("listenWithContainerFactoryTwo : {}", invoice);
	}
}
