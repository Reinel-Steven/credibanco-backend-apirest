package com.credibanco.assessment.card.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.credibanco.assessment.card.services.ITransactionService;
import com.credibanco.assessment.card.util.ValidateBody;

import jakarta.validation.Valid;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/card")
public class CardRestController {

	@Autowired
	private ICardService cardService;
	
	@GetMapping
	public ResponseEntity<?> listCard(){
		
		List<Card> cards = 	cardService.findAll();	
		if(cards!=null && cards.size()>0) {
			List<ConsultCardResponseDto> response = new ArrayList<ConsultCardResponseDto>();
			for(Card card: cards) {
				ConsultCardResponseDto newCardDto = new ConsultCardResponseDto(card);
				response.add(newCardDto);
			}; 
			return ResponseEntity.ok(response);	
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
	
	@PutMapping("/enroll-card/{pan}/{validate}")
	public ResponseEntity<?> enrollCard(@PathVariable("pan") String pan, @PathVariable("validate") int validateNumber){
		
		if(ValidateBody.isNumeric(pan)) {
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
			List<String> response= ValidateBody.errors(pan);
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
	public ResponseEntity<?> delete(@RequestParam String pan, @RequestParam int validateNumber) {
		
		try {
			
			Card card = cardService.findByPan(pan);
			if(card != null && card.getValidateNumber() == validateNumber) {
				cardService.delete(card.getId());
				return ResponseEntity.ok(DeleteStatus.APPROVED);
			}else {				
				return new ResponseEntity<DeleteStatus>(DeleteStatus.REJECTED, HttpStatus.NOT_FOUND);
			}
		}catch (DataAccessException e) {
			return new ResponseEntity<DeleteStatus>(DeleteStatus.REJECTED, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
