package com.credibanco.assessment.card.dto;

import java.io.Serializable;

import com.credibanco.assessment.card.constants.CodeCard;
import com.credibanco.assessment.card.model.Card;

public class CreateCardResponseDto implements Serializable{
	

	private static final long serialVersionUID = -1537431916780288708L;
	
	private int valiteNumber;
	private String maskPan; 
	private String code;
	private String message;
	
	/* *************** Constructor ******************* */
	public CreateCardResponseDto(Card card, CodeCard code) {
		this.valiteNumber = card.getValidateNumber();
		setMaskPan(card.getPan()); 
		this.setCode(code.getCodeNumber());
		this.message = code.getCodeMessage();
	}
	
	/* *************** Getter and Setter ******************* */

	public int getValiteNumber() {
		return valiteNumber;
	}
	public void setValiteNumber(int valiteNumber) {
		this.valiteNumber = valiteNumber;
	}
	public String getMaskPan() {
		return maskPan; 
	}
	public void setMaskPan(String maskPan) {
		String init = maskPan.substring(0,6);
		String fin =  maskPan.substring(maskPan.length()-4,maskPan.length());
		this.maskPan = init + "****" + fin;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
