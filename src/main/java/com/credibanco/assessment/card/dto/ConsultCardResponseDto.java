package com.credibanco.assessment.card.dto;

import java.io.Serializable;

import com.credibanco.assessment.card.model.Card;

public class ConsultCardResponseDto implements Serializable{

	private static final long serialVersionUID = -7764035368961670276L;
	
	private String pan;
	private String cardholder;
	private String cedula;
	private String phoneNumber;
	private Boolean enable;
	
	/************* Constructor***************************************** */
	public ConsultCardResponseDto(Card card) {
		this.pan = card.getPan();
		this.cardholder = card.getCardholder();
		this.cedula = card.getCedula();
		this.phoneNumber = card.getPhoneNumber();
		this.enable = card.getEnable();
	}
		
	/* *************** Getter and Setter ******************* */
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		String init = pan.substring(0,6);
		String fin =  pan.substring(pan.length()-4,pan.length());
		this.pan = init + "****" + fin;
	}
	public String getCardholder() {
		return cardholder;
	}
	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	
	
}
