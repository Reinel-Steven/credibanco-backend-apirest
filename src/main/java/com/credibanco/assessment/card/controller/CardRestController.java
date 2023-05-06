package com.credibanco.assessment.card.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.credibanco.assessment.card.constants.CodeCard;
import com.credibanco.assessment.card.constants.DeleteStatus;
import com.credibanco.assessment.card.constants.EnrollTransaction;
import com.credibanco.assessment.card.dto.ConsultCardResponseDto;
import com.credibanco.assessment.card.dto.CreateCardRequestDto;
import com.credibanco.assessment.card.dto.CreateCardResponseDto;
import com.credibanco.assessment.card.dto.EnrollCardResponseDto;
import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.services.ICardService;
import com.credibanco.assessment.card.util.ValidateBody;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/card")
public class CardRestController {

	@Autowired
	private ICardService cardService;
	
	@GetMapping
	public ResponseEntity<?> listCard(){
		
		List<Card> cards = 	cardService.findAll();	
		if(cards!=null && cards.size()>0) {
			return ResponseEntity.ok(cards);	
		}else {
			return ResponseEntity.notFound().build();
		}		
	}
		
	@PostMapping("/create")
	public ResponseEntity<?> createCard(@Valid @RequestBody CreateCardRequestDto body, BindingResult result) {

		if(ValidateBody.errors(body, result).isEmpty()) {
			CreateCardResponseDto response = null;
			Card newCard = new Card(body);
			try {
				response = new CreateCardResponseDto(newCard, CodeCard.SUCCESS);
				newCard = cardService.save(newCard); 
				return new ResponseEntity<CreateCardResponseDto>(response, HttpStatus.CREATED);
			} catch (DataAccessException e) {
				Map<String, Object> responses = new HashMap<>();
				response = new CreateCardResponseDto(newCard, CodeCard.FAILED);
				responses.put("Response", response);
				responses.put("Exception", e.getMostSpecificCause().getMessage());				
				return new ResponseEntity<Map<String, Object>>(responses, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			List<String> response= ValidateBody.errors(body, result);
			return new ResponseEntity<List<String>>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/enroll-card")
	public ResponseEntity<?> enrollCard(@Valid @RequestParam String pan, @RequestParam int validateNumber,  BindingResult result){
		
		if(ValidateBody.errors(pan, result).isEmpty()) {
			EnrollCardResponseDto response = null;
			Card card = cardService.findByPan(pan);
			try {			
				card = cardService.save(card);
				if(card==null) {
					response = new EnrollCardResponseDto(card, EnrollTransaction.CARD_NOT_EXIST);
					return new ResponseEntity<EnrollCardResponseDto>(response, HttpStatus.NOT_FOUND);
				}else if(card.getValidateNumber() == validateNumber) {
					card.setEnable(true);
					 cardService.save(card);
					response = new EnrollCardResponseDto(card, EnrollTransaction.SUCCESS);
					return ResponseEntity.ok(response);
				}else {
					response = new EnrollCardResponseDto(card, EnrollTransaction.INVALID_NUMBER);
					return new ResponseEntity<EnrollCardResponseDto>(response, HttpStatus.NOT_FOUND);
				}			
			} catch (DataAccessException e) {							
				return new ResponseEntity<String>(e.getMostSpecificCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			List<String> response= ValidateBody.errors(pan, result);
			return new ResponseEntity<List<String>>(response, HttpStatus.BAD_REQUEST);
		}				
	}
	
	@GetMapping("/{pan}")
	public ResponseEntity<?> consultCard(@PathVariable String pan) {
		try {
		Card card = cardService.findByPan(pan);
		ConsultCardResponseDto response = new ConsultCardResponseDto(card);
		if(card != null) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.notFound().build();
		}catch (DataAccessException e) {
			return new ResponseEntity<String>(e.getMostSpecificCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete")	
	public ResponseEntity<?> delete(@RequestParam String pan, @RequestParam String validateNumber) {
		
		try {
			Card card = cardService.findByPan(pan);
			if(card != null) {
				cardService.delete(card.getId());
				return ResponseEntity.ok("");
			}else {				
				return new ResponseEntity<DeleteStatus>(DeleteStatus.REJECTED, HttpStatus.NOT_FOUND);
			}
		}catch (DataAccessException e) {
			return new ResponseEntity<DeleteStatus>(DeleteStatus.REJECTED, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
