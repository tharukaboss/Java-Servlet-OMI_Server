package com.maanadev.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import com.maanadev.cards.CARD;
import com.maanadev.cards.SUIT;
import com.maanadev.messages.ImageBind;
import com.maanadev.messages.Message;
import com.maanadev.messages.Response;

public class PlayerONEOperationsImplement extends PlayerSuper implements PlayerOperations {

	public PlayerONEOperationsImplement() {
		super();
	}

	public void removeCard(int cardNum) {
		synchronized (hand) {
			hand.remove(cardNum);
		}
	}

	public void assignCards(CARD[] cards, int position) {

		synchronized (hand) {

			for (int i = position; i < (position + 13); i++) {
				CARD card = cards[i];
				int value = card.getcardVaule() + card.getSuit().getcardVaule();

				hand.put(value, card);
			}
		}

	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {

		return userId;
	}

	public synchronized void setCard1(CARD card) {
		this.card1 = card;
	}

	public synchronized void setCard2(CARD card) {
		this.card2 = card;

	}

	public synchronized void setCard3(CARD card) {
		this.card3 = card;
	}

	public synchronized void setMyCard(CARD card) {
		this.myCard = card;
	}

	public synchronized void setShowHand(boolean showHand) {
		this.showHand = showHand;
	}

	public synchronized void incrementPoint() {
		points++;
	}

	public synchronized int getPoint() {
		return points;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Response handleSSERequest(HttpServletRequest req, PLAYER playerContex) {

		Response res = new Response();
		res.setMessage(generateStatusMessage(false));
		res.setUserId(getUserId());
		return res;

	}

	public Message generateStatusMessage(boolean initial) {
		Message m = new Message();

		if (card1 != null) {
			m.setCard1(card1.getName());
		}

		if (card2 != null)
			m.setCard2(card2.getName());

		if (card3 != null)
			m.setCard3(card3.getName());

		if (myCard != null)
			m.setMycard(myCard.getName());

		m.setShowCards(showCards);
		m.setShowHand(showHand);
		m.setMessage(message);
		m.setShowCards(showCards);
		ArrayList<ImageBind> cardhand = new ArrayList<ImageBind>();

		for (CARD card : hand.values()) {
			ImageBind im = new ImageBind();
			im.setImage(card.getName());

			cardhand.add(im);
		}
		m.setCards(cardhand);

		return m;
	}

	public void setShowCards(boolean showCards) {
		this.showCards = showCards;
	}

	public CARD getCard(int cardNum) {
		synchronized (hand) {
			return hand.get(cardNum);
		}
	}

	public boolean isCardThere(SUIT suit) {
		synchronized (hand) {
				for(CARD card :hand.values()){
					if(card.getSuit()==suit){
						return true;
					}
				} 
		}
		return false;
		
	}

}
