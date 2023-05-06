package com.credibanco.assessment.card.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credibanco.assessment.card.constants.CancelTransactionCode;
import com.credibanco.assessment.card.constants.CodeTransaction;
import com.credibanco.assessment.card.dto.CancelTransactionRequestDto;
import com.credibanco.assessment.card.dto.TransactionRequestDto;
import com.credibanco.assessment.card.dto.TransactionResponseDto;
import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.model.TransactionCard;
import com.credibanco.assessment.card.services.ICardService;
import com.credibanco.assessment.card.services.ITransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionRestController {
	
	@Autowired
	private ICardService cardService;
	@Autowired
	private ITransactionService transactionService; 
	
	@GetMapping
	public ResponseEntity<?> listTransaction(){
		
		List<TransactionCard> transactions = transactionService.findAll();	
		if(transactions!=null && transactions.size()>0) {
			return ResponseEntity.ok(transactions);	
		}else {
			return ResponseEntity.notFound().build();
		}		
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createTransaction(@RequestBody TransactionRequestDto body) {
		TransactionResponseDto response = null;
		try {
			Card card = cardService.findByPan(body.getPan());
			if(!card.getEnable()) {
				response = new TransactionResponseDto(CodeTransaction.CARD_NOT_ENROLLED, body.getRef());
			}
			TransactionCard newTransaction = new TransactionCard(body, card);		
			response = new TransactionResponseDto(CodeTransaction.SUCCESS_PAY, body.getRef());
			newTransaction = transactionService.save(newTransaction); 
			return new ResponseEntity<TransactionResponseDto>(response, HttpStatus.CREATED);
		} catch (Exception e) {			
			response = new TransactionResponseDto(CodeTransaction.CARD_NOT_EXIST, body.getRef());			
			return new ResponseEntity<TransactionResponseDto>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> cancelTransaction(@RequestBody CancelTransactionRequestDto transaction){
		TransactionResponseDto response = null;
		try {
			
			TransactionCard transactionOld = transactionService.findByRef(transaction.getRef());
			Date dateCurren =  new Date();
			int minutes = hoursDifference(transactionOld.getCreated(), dateCurren);
			if(minutes<=5) {
				transactionOld.setApproved(false);
				transactionService.save(transactionOld);
				response = new TransactionResponseDto(CancelTransactionCode.CANSEL_SUCCESS, transaction.getRef());
				return ResponseEntity.ok(response);
			}else {
				response = new TransactionResponseDto(CancelTransactionCode.NOT_CANCEL_TRANSACTION, transaction.getRef());
				return new ResponseEntity<TransactionResponseDto>(response, HttpStatus.NOT_FOUND);
			}
		}catch (Exception e) {
			response = new TransactionResponseDto(CancelTransactionCode.REF_INVALID, transaction.getRef());
			return new ResponseEntity<TransactionResponseDto>(response, HttpStatus.NOT_FOUND);
		}
	}

/*************************************************/
	
	
	private int hoursDifference(Date init, Date fin) {
		int hoursDif = (int) (fin.getTime() - init.getTime()); 
		return ((hoursDif/1000)/60);
	}
}
