package com.maanadev.messages;

public class PlayReq {

	private String card;
	public PlayReq() {
	}
	private String userId;
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
