package com.credibanco.assessment.card.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credibanco.assessment.card.model.TransactionCard;
import com.credibanco.assessment.card.models.repository.TransactionRepository;
import com.credibanco.assessment.card.services.ITransactionService;

@Service
public class TransactionServiceImpl implements ITransactionService {

	@Autowired
	private TransactionRepository repo;
	
	@Override
	public List<TransactionCard> findAll() {
		return repo.findAll();
	}

	@Override
	public TransactionCard findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public TransactionCard save(TransactionCard transaction) {
		return repo.save(transaction);
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}

	@Override
	public TransactionCard findByRef(String ref) {
		return repo.findByRef(ref);
	}

}
