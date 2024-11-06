package com.course.rabbitmq.consumer.stream.config;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.config.StreamRabbitListenerContainerFactory;
import org.springframework.rabbit.stream.listener.StreamListenerContainer;

import com.rabbitmq.stream.Environment;
import com.rabbitmq.stream.OffsetSpecification;

@Configuration
public class RabbitmqStreamConfig {

	public static final String STREAM_NUMBER = "s.number";

	private static final String CONSUMER_OFFSET_ABSOLUTE_01 = "consumer-offset-absolute-01";
	private static final String CONSUMER_OFFSET_FIRST_01 = "consumer-offset-first-01";
	private static final String CONSUMER_OFFSET_LAST_01 = "consumer-offset-last-01";
	private static final String CONSUMER_OFFSET_NEXT_01 = "consumer-offset-next-01";
	private static final String CONSUMER_OFFSET_TIMESTAMP_01 = "consumer-offset-timestamp-01";
	private static final String CONSUMER_SINGLE_ACTIVE_01 = "consumer-single-active-01";

	private static final String CONSUMER_OFFSET_ABSOLUTE_02 = "consumer-offset-absolute-02";
	private static final String CONSUMER_OFFSET_FIRST_02 = "consumer-offset-first-02";
	private static final String CONSUMER_OFFSET_LAST_02 = "consumer-offset-last-02";
	private static final String CONSUMER_OFFSET_NEXT_02 = "consumer-offset-next-02";
	private static final String CONSUMER_OFFSET_TIMESTAMP_02 = "consumer-offset-timestamp-02";

	private static final String CONSUMER_OFFSET_FIRST_03 = "consumer-offset-first-03";

	@Bean
	RabbitListenerContainerFactory<StreamListenerContainer> absoluteContainerFactoryOne(Environment env) {
		var factory = new StreamRabbitListenerContainerFactory(env);

		factory.setNativeListener(true);
		factory.setConsumerCustomizer((id, builder) -> {
			builder.name(CONSUMER_OFFSET_ABSOLUTE_01).offset(OffsetSpecification.offset(3)).autoTrackingStrategy();
		});

		return factory;
	}

	@Bean
	RabbitListenerContainerFactory<StreamListenerContainer> firstContainerFactoryOne(Environment env) {
		var factory = new StreamRabbitListenerContainerFactory(env);

		factory.setNativeListener(true);
		factory.setConsumerCustomizer((id, builder) -> {
			builder.name(CONSUMER_OFFSET_FIRST_01).offset(OffsetSpecification.first()).autoTrackingStrategy();
		});

		return factory;
	}

	@Bean
	RabbitListenerContainerFactory<StreamListenerContainer> lastContainerFactoryOne(Environment env) {
		var factory = new StreamRabbitListenerContainerFactory(env);

		factory.setNativeListener(true);
		factory.setConsumerCustomizer((id, builder) -> {
			builder.name(CONSUMER_OFFSET_LAST_01).offset(OffsetSpecification.last()).autoTrackingStrategy();
		});

		return factory;
	}

	@Bean
	RabbitListenerContainerFactory<StreamListenerContainer> nextContainerFactoryOne(Environment env) {
		var factory = new StreamRabbitListenerContainerFactory(env);

		factory.setNativeListener(true);
		factory.setConsumerCustomizer((id, builder) -> {
			builder.name(CONSUMER_OFFSET_NEXT_01).offset(OffsetSpecification.next()).autoTrackingStrategy();
		});

		return factory;
	}

	@Bean
	RabbitListenerContainerFactory<StreamListenerContainer> timestampContainerFactoryOne(Environment env) {
		var timestampForOffset = ZonedDateTime.now(ZoneOffset.UTC).minusMinutes(5).toEpochSecond() * 1000;
		var factory = new StreamRabbitListenerContainerFactory(env);

		factory.setNativeListener(true);
		factory.setConsumerCustomizer((id, builder) -> {
			builder.name(CONSUMER_OFFSET_TIMESTAMP_01).offset(OffsetSpecification.timestamp(timestampForOffset))
					.autoTrackingStrategy();
		});

		return factory;
	}

	@Bean
	RabbitListenerContainerFactory<StreamListenerContainer> absoluteContainerFactoryTwo(Environment env) {
		var factory = new StreamRabbitListenerContainerFactory(env);

		factory.setNativeListener(true);
		factory.setConsumerCustomizer((id, builder) -> {
			builder.name(CONSUMER_OFFSET_ABSOLUTE_02).offset(OffsetSpecification.offset(3)).autoTrackingStrategy();
		});

		return factory;
	}

	@Bean
	RabbitListenerContainerFactory<StreamListenerContainer> firstContainerFactoryTwo(Environment env) {
		var factory = new StreamRabbitListenerContainerFactory(env);

		factory.setNativeListener(true);
		factory.setConsumerCustomizer((id, builder) -> {
			builder.name(CONSUMER_OFFSET_FIRST_02).offset(OffsetSpecification.first()).autoTrackingStrategy();
		});

		return factory;
	}

	@Bean
	RabbitListenerContainerFactory<StreamListenerContainer> lastContainerFactoryTwo(Environment env) {
		var factory = new StreamRabbitListenerContainerFactory(env);

		factory.setNativeListener(true);
		factory.setConsumerCustomizer((id, builder) -> {
			builder.name(CONSUMER_OFFSET_LAST_02).offset(OffsetSpecification.last()).autoTrackingStrategy();
		});

		return factory;
	}

	@Bean
	RabbitListenerContainerFactory<StreamListenerContainer> nextContainerFactoryTwo(Environment env) {
		var factory = new StreamRabbitListenerContainerFactory(env);

		factory.setNativeListener(true);
		factory.setConsumerCustomizer((id, builder) -> {
			builder.name(CONSUMER_OFFSET_NEXT_02).offset(OffsetSpecification.next()).autoTrackingStrategy();
		});

		return factory;
	}

	@Bean
	RabbitListenerContainerFactory<StreamListenerContainer> timestampContainerFactoryTwo(Environment env) {
		var timestampForOffset = ZonedDateTime.now(ZoneOffset.UTC).minusMinutes(5).toEpochSecond() * 1000;
		var factory = new StreamRabbitListenerContainerFactory(env);

		factory.setNativeListener(true);
		factory.setConsumerCustomizer((id, builder) -> {
			builder.name(CONSUMER_OFFSET_TIMESTAMP_02).offset(OffsetSpecification.timestamp(timestampForOffset))
					.autoTrackingStrategy();
		});

		return factory;
	}

	@Bean
	RabbitListenerContainerFactory<StreamListenerContainer> firstContainerFactoryThree(Environment env) {
		var factory = new StreamRabbitListenerContainerFactory(env);

		factory.setNativeListener(true);
		factory.setConsumerCustomizer((id, builder) -> {
			builder.name(CONSUMER_OFFSET_FIRST_03).offset(OffsetSpecification.first()).manualTrackingStrategy();
		});

		return factory;
	}

	@Bean
	RabbitListenerContainerFactory<StreamListenerContainer> singleActiveContainerFactoryOne(Environment env) {
		var factory = new StreamRabbitListenerContainerFactory(env);

		factory.setNativeListener(true);
		factory.setConsumerCustomizer((id, builder) -> {
			builder.name(CONSUMER_SINGLE_ACTIVE_01).offset(OffsetSpecification.next()).singleActiveConsumer()
					.autoTrackingStrategy();
		});

		return factory;
	}

}
