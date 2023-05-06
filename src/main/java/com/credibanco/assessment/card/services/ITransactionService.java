package com.credibanco.assessment.card.services;

import java.util.List;

import com.credibanco.assessment.card.model.TransactionCard;

public interface ITransactionService {
	
	public List<TransactionCard> findAll();
	
	public TransactionCard findById(Long id);
	
	public TransactionCard findByRef(String ref);
	
	public TransactionCard save(TransactionCard transaction);
	
	public void delete(Long id);
}
