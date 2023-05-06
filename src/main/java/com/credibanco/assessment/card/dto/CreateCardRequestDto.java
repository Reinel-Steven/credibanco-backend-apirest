package com.credibanco.assessment.card.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CreateCardRequestDto implements Serializable{
	
	
	private static final long serialVersionUID = 9127976376567782083L;
	
	@NotEmpty(message = "El numero de tarjeta es necesario")
	@Size(min = 16, max = 19, message = "la cantidad de digitos debe estar entre 16 y 19")
	private String pan;
	
	@NotEmpty(message = "El titular de la tarjeta es necesario")
	private String cardholder;
	
	@NotEmpty(message = "La cedula es necesaria")
	@Size(min = 10, max = 15, message = "la cantidad de digitos debe estar entre 10 y 15")
	private String cedula;
	
	@NotEmpty(message = "seleccione Debito o Credito")
	private String type;
	
	@NotEmpty(message = "El numero de telefono es necesario")
	@Size(min = 10, max = 10, message = "la cantidad de digitos debe ser 10")
	private String phoneNumber;
	
	
	/* *************** Getter and Setter ******************* */
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
