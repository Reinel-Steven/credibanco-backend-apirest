package com.credibanco.assessment.card.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credibanco.assessment.card.constants.CancelTransactionCode;
import com.credibanco.assessment.card.constants.CodeTransaction;
import com.credibanco.assessment.card.dto.CancelTransactionRequestDto;
import com.credibanco.assessment.card.dto.TransactionDto;
import com.credibanco.assessment.card.dto.TransactionRequestDto;
import com.credibanco.assessment.card.dto.TransactionResponseDto;
import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.model.TransactionCard;
import com.credibanco.assessment.card.services.ICardService;
import com.credibanco.assessment.card.services.ITransactionService;
import com.credibanco.assessment.card.util.ValidateBody;

import jakarta.validation.Valid;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/transaction")
public class TransactionRestController {
	
	@Autowired
	private ICardService cardService;
	@Autowired
	private ITransactionService transactionService; 
	
	@GetMapping
	public ResponseEntity<?> listTransaction(){
		
		try {
			List<TransactionCard> transactions = transactionService.findAll();
			List<TransactionDto> listResponse = new ArrayList<TransactionDto>();
			if(transactions!=null && transactions.size()>0) {					
				for(TransactionCard t: transactions) {
					TransactionDto transaction = new TransactionDto(t);
					listResponse.add(transaction);
				}				
				return ResponseEntity.ok(listResponse);	
			}else {
				return ResponseEntity.notFound().build();
			}	
		}catch (DataAccessException e) {
			return new ResponseEntity<String>(e.getMostSpecificCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionRequestDto body, BindingResult result) {
		if(ValidateBody.errors(body, result).isEmpty()) {
			TransactionResponseDto response = null;
			try {
				Card card = cardService.findByPan(body.getPan());
				if(card==null) {
					response = new TransactionResponseDto(CodeTransaction.CARD_NOT_EXIST, body.getRef());			
					return new ResponseEntity<TransactionResponseDto>(response, HttpStatus.NOT_FOUND);
				}else if(!card.getEnable()) {
					response = new TransactionResponseDto(CodeTransaction.CARD_NOT_ENROLLED, body.getRef());
					return new ResponseEntity<TransactionResponseDto>(response, HttpStatus.ACCEPTED);
				}
				TransactionCard newTransaction = new TransactionCard(body, card);		
				response = new TransactionResponseDto(CodeTransaction.SUCCESS_PAY, body.getRef());
				newTransaction = transactionService.save(newTransaction); 
				return new ResponseEntity<TransactionResponseDto>(response, HttpStatus.CREATED);
			} catch (DataAccessException e) {			
				return new ResponseEntity<String>(e.getMostSpecificCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			List<String> response= ValidateBody.errors(body, result);
			return new ResponseEntity<List<String>>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> cancelTransaction(@Valid @RequestBody CancelTransactionRequestDto body, BindingResult result){
		if(ValidateBody.errors(body, result).isEmpty()) {
			TransactionResponseDto response = null;
			try {			
				TransactionCard transactionOld = transactionService.findByRef(body.getRef());
				Date dateCurren =  new Date();
				int minutes = hoursDifference(transactionOld.getCreated(), dateCurren);
				if(minutes<=5) {
					transactionOld.setApproved(false);
					transactionService.save(transactionOld);
					response = new TransactionResponseDto(CancelTransactionCode.CANSEL_SUCCESS, body.getRef());
					return ResponseEntity.ok(response);
				}else {
					response = new TransactionResponseDto(CancelTransactionCode.NOT_CANCEL_TRANSACTION, body.getRef());
					return new ResponseEntity<TransactionResponseDto>(response, HttpStatus.NOT_FOUND);
				}
			}catch (Exception e) {
				response = new TransactionResponseDto(CancelTransactionCode.REF_INVALID, body.getRef());
				return new ResponseEntity<TransactionResponseDto>(response, HttpStatus.NOT_FOUND);
			}
		}else {
			List<String> response= ValidateBody.errors(body, result);
			return new ResponseEntity<List<String>>(response, HttpStatus.BAD_REQUEST);
		}
	}

/*************************************************/
	
	
	private int hoursDifference(Date init, Date fin) {
		int hoursDif = (int) (fin.getTime() - init.getTime()); 
		return ((hoursDif/1000)/60);
	}
}
