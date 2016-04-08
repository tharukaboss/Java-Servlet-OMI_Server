package com.maanadev.messages;

import java.util.ArrayList;

public class Message {
	public Message() {
	}
	
	


	private boolean showCards;
	private boolean showHand;
	private ArrayList<ImageBind> cards;
	private String card1;
	private String card2;
	private String card3;
	private String mycard;
	private String message;
	public boolean isShowCards() {
		return showCards;
	}


	public void setShowCards(boolean showCards) {
		this.showCards = showCards;
	}


	public boolean isShowHand() {
		return showHand;
	}


	public void setShowHand(boolean showHand) {
		this.showHand = showHand;
	}


	public String getCard1() {
		return card1;
	}


	public void setCard1(String card1) {
		this.card1 = card1;
	}


	public String getCard2() {
		return card2;
	}


	public void setCard2(String card2) {
		this.card2 = card2;
	}


	public String getCard3() {
		return card3;
	}


	public void setCard3(String card3) {
		this.card3 = card3;
	}


	public String getMycard() {
		return mycard;
	}


	public void setMycard(String mycard) {
		this.mycard = mycard;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public ArrayList<ImageBind> getCards() {
		return cards;
	}


	public void setCards(ArrayList<ImageBind> cards) {
		this.cards = cards;
	}
}
