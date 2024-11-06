package com.course.rabbitmq.consumer.entity;

public class ReportRequest {

	private String reportName;

	private boolean large;

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public boolean isLarge() {
		return large;
	}

	public void setLarge(boolean large) {
		this.large = large;
	}

	@Override
	public String toString() {
		return "ReportRequest [reportName=" + reportName + ", large=" + large + "]";
	}

}
