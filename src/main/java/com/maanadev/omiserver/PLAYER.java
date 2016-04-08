package com.maanadev.omiserver;

import java.util.HashMap;

import com.maanadev.cards.CARD;

enum  PLAYER {
	
	
	PLAYERONE(new HashMap<Integer,CARD> ()),
	PLAYERTWO(new HashMap<Integer,CARD> ()),
	PLAYERTHREE(new HashMap<Integer,CARD> ()),
	PLAYERFOUR(new HashMap<Integer,CARD> ());
	
	private String userID;
	private HashMap<Integer,CARD> cards;
	
	PLAYER(HashMap<Integer, CARD> cards){
		this.cards = cards ;
		
	}
	public void put(int cardValue,CARD card){
		synchronized (cards) {
			cards.put(cardValue, card);
		}
	}
	public CARD get(int cardValue){
		synchronized (cards) {
			return cards.get(cardValue);
		}
	}
	public void removeCard(int cardValue){
		synchronized (cards) {
			cards.remove(cardValue);
		}
	}
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
}
