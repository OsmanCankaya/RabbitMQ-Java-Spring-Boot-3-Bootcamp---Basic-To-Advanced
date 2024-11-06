package com.course.rabbitmq.two.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;

import com.rabbitmq.stream.Environment;

@Configuration
public class RabbitmqStreamConfig {

	@Bean
	@Qualifier("rabbitStreamTemplateHello")
	RabbitStreamTemplate rabbitStreamTemplateHello(Environment env) {
		var template = new RabbitStreamTemplate(env, "s.hello");
		return template;
	}

}
