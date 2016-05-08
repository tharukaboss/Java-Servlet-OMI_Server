package com.maanadev.messages;

public class PlayerCardChangeReq {

	
	private String userId;
	private String card;
	
	public PlayerCardChangeReq() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}
}
