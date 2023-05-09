package com.credibanco.assessment.card.dto;

import java.io.Serializable;

import com.credibanco.assessment.card.constants.EnrollTransaction;
import com.credibanco.assessment.card.model.Card;

public class EnrollCardResponseDto implements Serializable{
	

	private static final long serialVersionUID = 7264714950524496004L;
	
	private String code;
	private String pan;
	private String message;
	
	
	
	public EnrollCardResponseDto(Card card, EnrollTransaction enroll) {
		this.code = enroll.getCodeNumber();
		this.message = enroll.getCodeMessage();
		setPan(card.getPan());
		
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPan() {
		String init = pan.substring(0,6);
		String fin =  pan.substring(pan.length()-4,pan.length());
		return  init + "****" + fin;
		
	}
	public void setPan(String pan) {
		String init = pan.substring(0,6);
		String fin =  pan.substring(pan.length()-4,pan.length());
		this.pan = init + "****" + fin;
	}
	public String getMenssage() {
		return message;
	}
	public void setMenssage(String menssage) {
		this.message = menssage;
	}
	
}
