package com.credibanco.assessment.card.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credibanco.assessment.card.model.Card;

public interface CardRepository extends JpaRepository<Card, Long>{

	
	public Card findByPan(String pan);
	
}
