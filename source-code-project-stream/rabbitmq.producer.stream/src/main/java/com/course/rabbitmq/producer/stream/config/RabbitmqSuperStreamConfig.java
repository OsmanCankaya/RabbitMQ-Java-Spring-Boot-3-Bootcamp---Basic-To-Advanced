package com.course.rabbitmq.producer.stream.config;

import org.springframework.rabbit.stream.config.SuperStream;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;

import com.rabbitmq.stream.Environment;

//@Configuration
public class RabbitmqSuperStreamConfig {

	public static final String SUPER_STREAM_NUMBER_NAME = "s.super.number";
	public static final int SUPER_STREAM_NUMBER_PARTITIONS = 3;

//	@Bean
	SuperStream superStreamNumber() {
		return new SuperStream(SUPER_STREAM_NUMBER_NAME, SUPER_STREAM_NUMBER_PARTITIONS);
	}

//	@Bean(name = "superStreamNumberTemplate")
	RabbitStreamTemplate superStreamNumberTemplate(Environment env) {
		var template = new RabbitStreamTemplate(env, SUPER_STREAM_NUMBER_NAME);

		template.setSuperStreamRouting(message -> {
			return message.getProperties().getMessageIdAsString();
		});

		return template;
	}

}
