package com.credibanco.assessment.card.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class TransactionRequestDto implements Serializable{

	private static final long serialVersionUID = -1994854621582929712L;
	@NotEmpty
	@Size(min = 16, max = 19, message = "la cantidad de digitos debe estar entre 16 y 19")
	private String pan;
	@NotEmpty
	@Size(min = 6, max = 6, message = "la cantidad de digitos debe ser 6")
	private String ref;
	private Double totalPay;
	@NotEmpty
	private String adressPay;
	
	
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public Double getTotalPay() {
		return totalPay;
	}
	public void setTotalPay(Double totalPay) {
		this.totalPay = totalPay;
	}
	public String getAdressPay() {
		return adressPay;
	}
	public void setAdressPay(String adressPay) {
		this.adressPay = adressPay;
	}
		
}
