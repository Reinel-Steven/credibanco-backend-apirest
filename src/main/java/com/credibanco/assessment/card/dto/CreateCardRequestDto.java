package com.credibanco.assessment.card.dto;

import java.io.Serializable;

public class CreateCardRequestDto implements Serializable{
	
	
	private static final long serialVersionUID = 9127976376567782083L;
	
	private String pan;
	private String cardholder;
	private String cedula;
	private String type;
	private String phoneNumber;
	
	
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
