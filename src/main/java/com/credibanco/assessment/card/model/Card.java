package com.credibanco.assessment.card.model;

import java.io.Serializable;
import java.util.Date;


import org.springframework.validation.annotation.Validated;

import com.credibanco.assessment.card.dto.CreateCardRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Validated
public class Card implements Serializable{

	private static final long serialVersionUID = 7502598443812147839L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
		
	@Column(unique=true, nullable=false)	
	private String pan;
		
	private String cardholder;
		
	@Column(unique=true)
	private String cedula;
		
	private String type;
	
	private String phoneNumber;
	
	private Boolean enable;

	private int validateNumber = 0;
	@Temporal(TemporalType.TIMESTAMP)
	private Date created; 
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;
	
	/************* Constructor***************************************** */
	
	
	public Card(CreateCardRequestDto body) {
		this.pan = body.getPan();
		this.cardholder = body.getCardholder();
		this.cedula = body.getCedula();
		this.type = body.getType();
		this.phoneNumber = body.getPhoneNumber();
		this.setEnable(false);
		this.validateNumber = (int)(Math.random()*100+1);
		this.created = new Date();
		this.modified = new Date();
	}
	
	public Card() {
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


	public int getValidateNumber() {
		return validateNumber;
	}

	public void setValidateNumber(int validateNumber) {
		this.validateNumber = validateNumber;
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

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	} 

}
