package com.credibanco.assessment.card.services;

import java.util.List;

import com.credibanco.assessment.card.model.Card;

public interface ICardService {

	public List<Card> findAll();
	
	public Card findById(Long id);
	
	public Card save(Card card);
	
	public void delete(Long id);
	
	public Card findByPan(String pan);
		
}
