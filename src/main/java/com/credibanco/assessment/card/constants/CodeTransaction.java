package com.credibanco.assessment.card.constants;

public enum CodeTransaction {
	SUCCESS_PAY("Compra exitosa", "00"), 
	CARD_NOT_EXIST("Tarjeta no existe", "01"),
	CARD_NOT_ENROLLED("Tarjeta no enrolada", "02");

	private String message;
	private String codeNumber;

	CodeTransaction(String message, String codeNumber) {
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
