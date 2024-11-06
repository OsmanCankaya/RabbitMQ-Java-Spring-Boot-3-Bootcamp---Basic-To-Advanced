package com.course.rabbitmq.producer.stream;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.rabbitmq.producer.stream.entity.Invoice;
import com.course.rabbitmq.producer.stream.producer.SuperStreamInvoiceProducer;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private SuperStreamInvoiceProducer producer;

	@Autowired
	private RabbitTemplate dummyRabbitTemplate;

	@Override
	public void run(String... args) throws Exception {
		dummyRabbitTemplate.convertAndSend("Test");

		for (int i = 0; i < 10; i++) {
			var invoiceAmount = ThreadLocalRandom.current().nextInt(10, 1000);
			var invoiceCreated = new Invoice("INV-" + i, Invoice.Status.CREATED, invoiceAmount);
			var invoiceApproved = new Invoice("INV-" + i, Invoice.Status.APPROVED, invoiceAmount);

			producer.sendInvoiceUsingRabbitStreamTemplate(invoiceCreated);
			producer.sendInvoiceUsingRabbitStreamTemplate(invoiceApproved);
		}

		System.out.println("All invoices sent");
	}

}
