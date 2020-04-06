package com.pctc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pctc.entity.Card;
import com.pctc.entity.CardExample;
import com.pctc.entity.CardExample.Criteria;
import com.pctc.entity.User;
import com.pctc.service.CardService;
import com.pctc.service.UserService;

@Controller
public class CardController {

	@Autowired
	private CardService cardService;
	@Autowired
	private UserService userService;

	//查询所有一卡通
	@RequestMapping("/cardList")
	public String cardList(Map<String, Object> map) {
		CardExample cardExample = new CardExample();
		Criteria criteria = cardExample.createCriteria();
		criteria.andDeleteFlagEqualTo(false);
		List<Card> cards = cardService.getCards(cardExample);
		map.put("cards", cards);
		return "card/card-list";
	}

	//条件查询一卡通
	@RequestMapping("selectCard")
	public String selectCard(@RequestParam(value = "datemin", required = false) Date datemin,
			@RequestParam(value = "datemax", required = false) Date datemax, Card card, Map<String, Object> map) {
		CardExample cardExample = new CardExample();
		Criteria criteria = cardExample.createCriteria();
		criteria.andDeleteFlagEqualTo(false);
		if (datemin!=null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(datemin);
		}
		if (datemax!=null) {
			criteria.andCreateTimeLessThanOrEqualTo(datemax);
		}
		if (!card.getStatus().isEmpty()) {
			criteria.andStatusEqualTo(card.getStatus());
		}
		if (card.getUserNumber()!=null) {
			criteria.andUserNumberEqualTo(card.getUserNumber());
		}
		List<Card> cards = cardService.getCards(cardExample);
		map.put("cards", cards);
		return "card/card-list";
	}
	
	@RequestMapping("cardInfo")
	public String cardInfo(Map<String, Object> map) {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		User user = (User) session.getAttribute("user");

		CardExample cardExample = new CardExample();
		Criteria criteria = cardExample.createCriteria();
		criteria.andUserNumberEqualTo(user.getUserNumber());
		criteria.andDeleteFlagEqualTo(false);
		List<Card> cards = cardService.getCards(cardExample);
		if (!cards.isEmpty()) {
			map.put("card", cards.get(0));
		} else {
			Card card = new Card();
			map.put("card", card);
		}
		return "card/card-info";
	}

	@RequestMapping("toAddCard")
	public String toAddCard() {
		return "card/card-add";
	}

	//办卡
	@ResponseBody
	@RequestMapping(value = "/addCard", method = RequestMethod.POST)
	public String addCard(Card card) {
		User user = userService.getUserByUserNumber(card.getUserNumber());
		if (user != null) {
			CardExample example=new CardExample();
			Criteria criteria=example.createCriteria();
			criteria.andUserNumberEqualTo(user.getUserNumber());
			criteria.andDeleteFlagEqualTo(false);
			List<Card> cards=cardService.getCards(example);
			if (cards.isEmpty()) {
				card.setPayPwd(user.getIdNumber().substring(12));
				card.setCreateTime(new Date());
				card.setUpdateTime(null);
				card.setStatus("正常");
				card.setDeleteFlag(false);
				cardService.add(card);
				return "1";
			}
			return "2";
		} else {
			return "0";
		}
		
	}
	
	@RequestMapping("toStopCard")
	public String toStopCard() {

		return "card/card-stop";
	}

	//卡挂失
	@ResponseBody
	@RequestMapping("stopCard")
	public String stopCard(Integer userNumber) {
		CardExample cardExample = new CardExample();
		Criteria criteria = cardExample.createCriteria();
		criteria.andUserNumberEqualTo(userNumber);
		criteria.andDeleteFlagEqualTo(false);
		List<Card> cards = cardService.getCards(cardExample);
		if (!cards.isEmpty()) {
			for (Card card : cards) {
				if ("正常".equals(card.getStatus())) {
					card.setStatus("挂失");
					cardService.update(card);
					return "1";
				}
			}
			return "2";
		}
		return "0";
	}

	//卡解挂
	@ResponseBody
	@RequestMapping("startCard")
	public boolean startCard(@RequestParam(value = "id") Integer id) {
		Card card = cardService.getCard(id);
		card.setStatus("正常");
		cardService.update(card);
		return true;
	}

	/*@RequestMapping("toRecharge")
	public String toRecharge(@RequestParam(value = "id") Integer id, Map<String, Object> map) {
		map.put("id", id);
		return "card/card-recharge";
	}*/
	
	@RequestMapping("toRecharge")
	public String toRecharge() {
		
		return "card/card-recharge";
	}

	//卡充值
	@ResponseBody
	@RequestMapping(value = "rechargeCard", method = RequestMethod.POST)
	public String rechargeCard(Integer userNumber, Integer money) {
		CardExample example=new CardExample();
		Criteria criteria=example.createCriteria();
		criteria.andUserNumberEqualTo(userNumber);
		criteria.andDeleteFlagEqualTo(false);
		List<Card> cards=cardService.getCards(example);
		if (!cards.isEmpty()) {
			Card card=cards.get(0);
			if (card.getStatus().equals("正常")) {
				card.setBalance(card.getBalance() + money);
				cardService.update(card);
				return "1";
			}else {
				return "2";
			}
		}else {
			return "0";
		}
		
	}
	
	@RequestMapping("toChangePayPwd")
	public String toChangePayPwd(@RequestParam(value = "id") Integer id, Map<String, Object> map) {
		map.put("id", id);
		return "card/card-changepaypwd";
	}
	
	//修改支付密码
	@ResponseBody
	@RequestMapping(value = "changePayPwd", method = RequestMethod.POST)
	public boolean changePayPwd(Integer id, String oldPayPwd,String newPayPwd) {
		Card card = cardService.getCard(id);
		if (oldPayPwd.equals(card.getPayPwd())) {
			card.setPayPwd(newPayPwd);
			card.setUpdateTime(new Date());
			cardService.update(card);
			return true;
		}	
		return false;
	}
	
	//删除卡
	@ResponseBody
	@RequestMapping(value = "deleteCard", method = RequestMethod.POST)
	public boolean deleteCard(Integer id) {
		cardService.delete(id);
		return true;
	}
	
	// 表单是时间类型的处理方法
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// true允许表单的值为空
	}


}
