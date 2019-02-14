package com.paytmlabs.demo.enums;

public enum ResponseStatus {

	 SUCCESS("success"),
	 WARN("warn"),
	 ERROR("error");
	
	private final String status;
	
	private ResponseStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
}
