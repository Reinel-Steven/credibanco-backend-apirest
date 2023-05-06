package com.credibanco.assessment.card.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.models.repository.CardRepository;
import com.credibanco.assessment.card.services.ICardService;

@Service
public class CardServiceImpl implements ICardService {

	
	@Autowired
	private CardRepository cardDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Card> findAll() {
		return cardDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Card findById(Long id) {		// 
		return cardDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Card save(Card card) {
		return cardDao.save(card);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		cardDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Card findByPan(String pan) {
		return cardDao.findByPan(pan);
	}

}
