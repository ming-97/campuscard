package com.pctc.service;

import java.util.List;

import com.pctc.entity.Card;
import com.pctc.entity.CardExample;

public interface CardService {

	public List<Card> getCards(CardExample cardExample);
	
	public Card getCard(Integer id);
	
	public int add(Card card);
	
	public int update(Card card);
	
	public int delete(Integer id);
	
}
