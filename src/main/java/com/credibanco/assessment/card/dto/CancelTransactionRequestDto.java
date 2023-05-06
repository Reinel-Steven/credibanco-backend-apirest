package com.credibanco.assessment.card.dto;

public class CancelTransactionRequestDto {

	private String pan;
	private String ref;
	private String totalPay;
	/************* Constructor***************************************** */
	public CancelTransactionRequestDto() {
		super();
		
	}
	/* *************** Getter and Setter ******************* */
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
	public String getTotalPay() {
		return totalPay;
	}
	public void setTotalPay(String totalPay) {
		this.totalPay = totalPay;
	}
}
