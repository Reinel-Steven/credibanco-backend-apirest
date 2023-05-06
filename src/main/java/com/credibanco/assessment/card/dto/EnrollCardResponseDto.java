package com.credibanco.assessment.card.dto;

import java.io.Serializable;

import com.credibanco.assessment.card.constants.EnrollTransaction;
import com.credibanco.assessment.card.model.Card;

public class EnrollCardResponseDto implements Serializable{
	

	private static final long serialVersionUID = 7264714950524496004L;
	
	private String code;
	private String pan;
	private String menssage;
	
	
	
	public EnrollCardResponseDto(Card card, EnrollTransaction enroll) {
		this.code = enroll.getCodeNumber();
		this.menssage = enroll.getCodeMessage();
		setPan(card.getPan());
		
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		String init = pan.substring(0,6);
		String fin =  pan.substring(pan.length()-5,pan.length());
		this.pan = init + "****" + fin;
	}
	public String getMenssage() {
		return menssage;
	}
	public void setMenssage(String menssage) {
		this.menssage = menssage;
	}
	
}
