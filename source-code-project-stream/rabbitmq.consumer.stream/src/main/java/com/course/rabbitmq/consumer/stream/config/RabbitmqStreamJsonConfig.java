package com.course.rabbitmq.consumer.stream.config;

import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.config.StreamRabbitListenerContainerFactory;
import org.springframework.rabbit.stream.listener.StreamListenerContainer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rabbitmq.stream.Environment;
import com.rabbitmq.stream.OffsetSpecification;

@Configuration
public class RabbitmqStreamJsonConfig {

	public static final String STREAM_INVOICE = "s.invoice";

	@Bean
	ObjectMapper objectMapper() {
		return JsonMapper.builder().findAndAddModules().build();
	}

	@Bean
	Jackson2JsonMessageConverter converter(@Autowired ObjectMapper objectMapper) {
		return new Jackson2JsonMessageConverter(objectMapper);
	}

	@Bean(name = "invoiceContainerFactoryOne")
	RabbitListenerContainerFactory<StreamListenerContainer> invoiceContainerFactoryOne(Environment env) {
		var factory = new StreamRabbitListenerContainerFactory(env);
		factory.setNativeListener(false);
		factory.setConsumerCustomizer((id, builder) -> {
			builder.name("invoice-consumer-one").offset(OffsetSpecification.first()).singleActiveConsumer()
					.autoTrackingStrategy();
		});

		return factory;
	}

	@Bean(name = "invoiceContainerFactoryTwo")
	RabbitListenerContainerFactory<StreamListenerContainer> invoiceContainerFactoryTwo(Environment env) {
		var factory = new StreamRabbitListenerContainerFactory(env);
		factory.setNativeListener(true);
		factory.setConsumerCustomizer((id, builder) -> {
			builder.name("invoice-consumer-two").offset(OffsetSpecification.first()).singleActiveConsumer()
					.autoTrackingStrategy();
		});

		return factory;
	}

}
