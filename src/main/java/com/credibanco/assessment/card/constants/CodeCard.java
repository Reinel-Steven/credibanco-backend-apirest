package com.credibanco.assessment.card.constants;

public enum CodeCard {
	SUCCESS("Éxito", "00"), 
	FAILED("Fallido", "01");

	private String message;
	private String codeNumber;

	CodeCard(String message, String codeNumber) {
		this.message = message;
		this.codeNumber = codeNumber;
	}
	public String getCodeMessage() {
		return message;
	}
	public String getCodeNumber() {
		return codeNumber;
	}
}
