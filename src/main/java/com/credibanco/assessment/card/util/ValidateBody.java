package com.credibanco.assessment.card.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.credibanco.assessment.card.dto.CancelTransactionRequestDto;
import com.credibanco.assessment.card.dto.CreateCardRequestDto;
import com.credibanco.assessment.card.dto.TransactionRequestDto;

public class ValidateBody {

	private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false; 
	    }
	    return pattern.matcher(strNum).matches();
	}
	
	public static List<String> errors (CreateCardRequestDto body){
		List<String> errors = new ArrayList<String>();
		if(isNumeric(body.getPan())) {
			errors.add("El campo PAN debe ser numerico");
		}
		if(isNumeric(body.getCedula())) {
			errors.add("El campo Cedula debe ser numerico");
		}
		if(isNumeric(body.getPhoneNumber())) {
			errors.add("El campo Telefono debe ser numerico");
		}
		return errors;
	}
	
	public static List<String> errors ( BindingResult result){
		List<String> errors = new ArrayList<String>();
		if(result.hasErrors()) {

			for(FieldError err: result.getFieldErrors()) {
				errors.add("el campo '" + err.getField().toString() + "' " + err.getDefaultMessage());
			}			
		}
		return errors;
	}
	
	public static List<String> errors (CreateCardRequestDto body, BindingResult result){
		List<String> errors = new ArrayList<String>();
		if(result.hasErrors()) {

			for(FieldError err: result.getFieldErrors()) {
				errors.add("el campo '" + err.getField().toString() + "' " + err.getDefaultMessage());
			}			
		}
		if(isNumeric(body.getPan())) {
			errors.add("El campo PAN debe ser numerico");
		}
		if(isNumeric(body.getCedula())) {
			errors.add("El campo Cedula debe ser numerico");
		}
		if(isNumeric(body.getPhoneNumber())) {
			errors.add("El campo Telefono debe ser numerico");
		}
		return errors;
	}
	
	public static List<String> errors (String number, BindingResult result){
		List<String> errors = new ArrayList<String>();
		if(result.hasErrors()) {

			for(FieldError err: result.getFieldErrors()) {
				errors.add("el campo '" + err.getField().toString() + "' " + err.getDefaultMessage());
			}			
		}
		if(isNumeric(number)) {
			errors.add("El campo debe ser numerico");
		}
		return errors;
	}
	
	//TransactionRequestDto
	public static List<String> errors (TransactionRequestDto body, BindingResult result){
		List<String> errors = new ArrayList<String>();
		if(result.hasErrors()) {

			for(FieldError err: result.getFieldErrors()) {
				errors.add("el campo '" + err.getField().toString() + "' " + err.getDefaultMessage());
			}			
		}
		if(isNumeric(body.getPan())) {
			errors.add("El campo PAN debe ser numerico");
		}
		if(isNumeric(body.getRef())) {
			errors.add("El campo Referencia debe ser numerico");
		}
		
		return errors;
	}
	
	public static List<String> errors (CancelTransactionRequestDto body, BindingResult result){
		List<String> errors = new ArrayList<String>();
		if(result.hasErrors()) {

			for(FieldError err: result.getFieldErrors()) {
				errors.add("el campo '" + err.getField().toString() + "' " + err.getDefaultMessage());
			}			
		}
		if(isNumeric(body.getPan())) {
			errors.add("El campo PAN debe ser numerico");
		}
		if(isNumeric(body.getRef())) {
			errors.add("El campo Referencia debe ser numerico");
		}
		
		return errors;
	}
}
