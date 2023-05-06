package com.credibanco.assessment.card.constants;

public enum CancelTransactionCode {
	CANSEL_SUCCESS("Compra anulada", "00"), 
	REF_INVALID("numero de referencia inválido", "01"),
	NOT_CANCEL_TRANSACTION("No se puede anular transacción", "02");

	private String message;
	private String codeNumber;

	CancelTransactionCode(String message, String codeNumber) {
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
