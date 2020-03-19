package com.pctc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pctc.entity.Card;
import com.pctc.entity.CardExample;
import com.pctc.mapper.CardMapper;
import com.pctc.service.CardService;

@Service("cardService")
public class CardServiceImpl implements CardService {

	@Autowired
	private CardMapper cardMapper;
	
	@Override
	public List<Card> getCards(CardExample cardExample) {
		return cardMapper.selectByExample(cardExample);
	}

	@Override
	public Card getCard(Integer id) {
		return cardMapper.selectByPrimaryKey(id);
	}

	@Override
	public int add(Card card) {
		return cardMapper.insert(card);
	}

	@Override
	public int update(Card card) {
		return cardMapper.updateByPrimaryKey(card);
	}

	@Override
	public int delete(Integer id) {
		int count=0;
		Card card=cardMapper.selectByPrimaryKey(id);
		if (card!=null) {
			card.setDeleteFlag(true);
			count=cardMapper.updateByPrimaryKey(card);
		}
		return count;
	}

}
