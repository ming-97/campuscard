package com.pctc.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pctc.entity.Card;
import com.pctc.service.CardService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCard {

	@Autowired 
	private CardService cardService;
	
	@Test
	public void testAdd() {
		
		Card card=new Card("666666", 1, "正常", 1000.00, new Date(), null, false);
		cardService.add(card);
	}
}
