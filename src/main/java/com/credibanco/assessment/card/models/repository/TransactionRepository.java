package com.credibanco.assessment.card.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credibanco.assessment.card.model.TransactionCard;

public interface TransactionRepository extends JpaRepository<TransactionCard, Long>{
	
	public TransactionCard findByRef(String ref);
}
