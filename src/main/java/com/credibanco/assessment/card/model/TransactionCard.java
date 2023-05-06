package com.credibanco.assessment.card.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.validation.annotation.Validated;

import com.credibanco.assessment.card.dto.TransactionRequestDto;
import com.credibanco.assessment.card.util.ValidateNumeric;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Validated
public class TransactionCard implements Serializable{

	
	private static final long serialVersionUID = 6288389797049507461L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	@Column(unique=true)
	@Size(min = 6, max = 6, message = "la cantidad de digitos debe ser 6")
	private String ref;

	private Double totalPay;
	@NotEmpty
	private String adressPay;
	
	private Boolean approved;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created; 
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;
	/* **************** relation many to one ******************* */
	@JsonIgnore 
	@JoinColumn(name="card_id")
	@ManyToOne(fetch = FetchType.EAGER )
	private Card card;
	
	
	/************* Constructor***************************************** */
	
	
	public TransactionCard(TransactionRequestDto transaction, Card card) {
		if(ValidateNumeric.isLong(transaction.getRef())){
			this.ref = transaction.getRef();
		}		
		this.totalPay = transaction.getTotalPay();
		this.adressPay = transaction.getAdressPay();
		this.approved = true;
		this.created = new Date();		
		this.modified = new Date();
		this.card = card;
	}
	
	public TransactionCard() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* *************** Getter and Setter ******************* */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	
}
