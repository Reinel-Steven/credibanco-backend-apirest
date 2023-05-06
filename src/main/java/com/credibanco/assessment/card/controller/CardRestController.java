package com.credibanco.assessment.card.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	public ResponseEntity<?> createCard(@RequestBody CreateCardRequestDto body) {
		
		Card newCard = new Card(body);
		CreateCardResponseDto response = null;
		try {
			response = new CreateCardResponseDto(newCard, CodeCard.SUCCESS);
			newCard = cardService.save(newCard); 
			return new ResponseEntity<CreateCardResponseDto>(response, HttpStatus.CREATED);
		} catch (Exception e) {			
			response = new CreateCardResponseDto(newCard, CodeCard.FAILED);			
			return new ResponseEntity<CreateCardResponseDto>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/enroll-card")
	public ResponseEntity<?> enrollCard(@RequestParam String pan, @RequestParam String validateNumber ){
		Card card = cardService.findByPan(pan);
		EnrollCardResponseDto response = null;
		try {			
			card = cardService.save(card);
			card.setEnable(true);
			response = new EnrollCardResponseDto(card, EnrollTransaction.SUCCESS);
			return ResponseEntity.ok(response);
		} catch (Exception e) {			
			response = new EnrollCardResponseDto(card, EnrollTransaction.CARD_NOT_EXIST);	
			return new ResponseEntity<EnrollCardResponseDto>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{pan}")
	public ResponseEntity<?> consultCard(@PathVariable String pan) {
		Card card = cardService.findByPan(pan);
		ConsultCardResponseDto response = new ConsultCardResponseDto(card);
		if(card != null) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/delete")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@RequestParam String pan, @RequestParam String validateNumber) {
		
		try {
			Card card = cardService.findByPan(pan);
			if(card != null) {
				cardService.delete(card.getId());
				return ResponseEntity.ok("");
			}else {				
				return new ResponseEntity<DeleteStatus>(DeleteStatus.REJECTED, HttpStatus.NOT_FOUND);
			}
		}catch (Exception e) {
			return new ResponseEntity<DeleteStatus>(DeleteStatus.REJECTED, HttpStatus.NOT_FOUND);
		}
	}
	
}
