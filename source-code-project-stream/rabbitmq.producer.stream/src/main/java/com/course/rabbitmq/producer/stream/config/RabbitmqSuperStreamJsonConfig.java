package com.course.rabbitmq.producer.stream.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.rabbit.stream.config.SuperStream;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;

import com.rabbitmq.stream.Environment;

//@Configuration
public class RabbitmqSuperStreamJsonConfig {

	public static final String SUPER_STREAM_INVOICE_NAME = "s.super.invoice";
	public static final int SUPER_STREAM_INVOICE_PARTITIONS = 3;

	@Bean
	SuperStream superStreamInvoice() {
		return new SuperStream(SUPER_STREAM_INVOICE_NAME, SUPER_STREAM_INVOICE_PARTITIONS);
	}

	@Bean(name = "superStreamInvoiceTemplate")
	RabbitStreamTemplate superStreamInvoiceTemplate(Environment env, Jackson2JsonMessageConverter jsonConverter) {
		var template = new RabbitStreamTemplate(env, SUPER_STREAM_INVOICE_NAME);
		template.setMessageConverter(jsonConverter);

		template.setSuperStreamRouting(message -> {
			return message.getProperties().getMessageIdAsString();
		});

		return template;
	}

}
