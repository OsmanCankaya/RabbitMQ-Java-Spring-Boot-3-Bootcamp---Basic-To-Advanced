package com.course.rabbitmq.producer.client;

import java.time.Duration;
import java.util.Base64;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.course.rabbitmq.producer.entity.RabbitmqQueue;

@Service
public class RabbitmqClient {

	public List<RabbitmqQueue> getAllQueues() {
		var webClient = WebClient.create("http://localhost:15672/api/queues");

		return webClient.get().header(HttpHeaders.AUTHORIZATION, createBasicAuthHeaders()).retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<RabbitmqQueue>>() {
				}).block(Duration.ofSeconds(10));

	}

	String createBasicAuthHeaders() {
		var auth = "guest:guest";

		return "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());
	}

}
