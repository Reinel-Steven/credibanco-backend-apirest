package com.credibanco.assessment.card.constants;

public enum StatusTransaction {
	APPROVED("Aprobada"),
	REJECTED("rechazada");
	
	private String status;
	
	StatusTransaction(String status){
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
}
