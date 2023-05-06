package com.credibanco.assessment.card.dto;

import java.io.Serializable;

import com.credibanco.assessment.card.constants.CancelTransactionCode;
import com.credibanco.assessment.card.constants.CodeTransaction;

public class TransactionResponseDto implements Serializable{

	
	private static final long serialVersionUID = -5957742437391693174L;

	
	private String refNumber;
	private String code;
	private String message;
	
	/************* Constructor***************************************** */
	public TransactionResponseDto(CodeTransaction code, String ref) {
		
		this.code = code.getCodeNumber();
		this.message = code.getCodeMessage();
		this.refNumber = ref;
	}
	public TransactionResponseDto(CancelTransactionCode code, String ref) {
		
		this.code = code.getCodeNumber();
		this.message = code.getCodeMessage();
		this.refNumber = ref;
	}
	/* *************** Getter and Setter ******************* */
	public String getRefNumber() {
		return refNumber;
	}
	
	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
		
}
