package com.course.rabbitmq.producer.stream.entity;

public class Invoice {

	public enum Status {
		CREATED, APPROVED, PAID, REJECTED
	}

	private String invoiceNumber;
	private Status status;
	private int amount;

	public Invoice() {

	}

	public Invoice(String invoiceNumber, Status status, int amount) {
		super();
		this.invoiceNumber = invoiceNumber;
		this.status = status;
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Invoice [invoiceNumber=" + invoiceNumber + ", status=" + status + ", amount=" + amount + "]";
	}

}
