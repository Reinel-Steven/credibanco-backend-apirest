package com.credibanco.assessment.card.dto;

import java.io.Serializable;
import java.util.Date;

import com.credibanco.assessment.card.model.TransactionCard;

public class TransactionDto implements Serializable{
	
	private static final long serialVersionUID = -93369547685084580L;

	private String pan;
	
	private String ref;

	private Double totalPay;

	private String adressPay;
	
	private Boolean approved;
	
	private Date created;

	
	
	public TransactionDto(TransactionCard t) {
		super();
		this.pan = t.getCard().getPan();
		this.ref = t.getRef();
		this.totalPay = t.getTotalPay();
		this.adressPay = t.getAdressPay();
		this.approved = t.getApproved();
		this.created = t.getCreated();
		
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

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getPan() {
		String init = pan.substring(0,6);
		String fin =  pan.substring(pan.length()-4,pan.length());
		return pan = init + "****" + fin;
	}
	public void setPan(String pan) {
		String init = pan.substring(0,6);
		String fin =  pan.substring(pan.length()-4,pan.length());
		this.pan = init + "****" + fin;
	}
	
	
}
