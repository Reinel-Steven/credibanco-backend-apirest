package com.credibanco.assessment.card.constants;

public enum EnrollTransaction {

	SUCCESS("Éxito", "00"), 
	CARD_NOT_EXIST("Tarjeta no existe", "01"),
	INVALID_NUMBER("Número de validación inválido", "02");

	private String message;
	private String codeNumber;

	EnrollTransaction(String message, String codeNumber) {
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
