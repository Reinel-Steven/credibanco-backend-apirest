package com.credibanco.assessment.card.constants;

public enum DeleteStatus {
	APPROVED("Se ha eliminado la tarjeta", "00"),
	REJECTED("No se ha eliminado la tarjeta", "01");
	
	private String message;
	private String codeNumber;
	
	DeleteStatus(String message, String codeNumber){
		this.message = message;
		this.codeNumber = codeNumber;
	}
	
	public String getMessage() {
		return message;
	}
	public String getCodeNumber() {
		return codeNumber;
	}
}
