package com.course.rabbitmq.consumer.stream.config;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.rabbit.stream.listener.StreamListenerContainer;

import com.course.rabbitmq.consumer.stream.entity.Invoice;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.stream.Environment;
import com.rabbitmq.stream.OffsetSpecification;

//@Configuration
public class RabbitmqSuperStreamJsonConfig {

	private static final Logger LOG = LoggerFactory.getLogger(RabbitmqStreamJsonConfig.class);

	@Autowired
	private ObjectMapper objectMapper;

	@Bean(name = "superStreamInvoiceContainer")
	StreamListenerContainer superStreamInvoiceContainer() {
		var env = Environment.builder().maxConsumersByConnection(1).build();
		var container = new StreamListenerContainer(env);

		container.setConsumerCustomizer((id, builder) -> builder.offset(OffsetSpecification.first()));
		container.superStream("s.super.invoice", "my-super-stream-invoice-consumer");
		container.setupMessageListener(message -> {
			try {
				var invoice = objectMapper.readValue(message.getBody(), Invoice.class);

				LOG.info("Invoice is : {}", invoice);

				TimeUnit.SECONDS.sleep(4);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return container;
	}

}
