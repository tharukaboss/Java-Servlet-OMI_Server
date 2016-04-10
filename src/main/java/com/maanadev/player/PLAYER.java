package com.maanadev.player;

import javax.servlet.http.HttpServletRequest;

import com.maanadev.cards.CARD;
import com.maanadev.messages.Message;
import com.maanadev.messages.Response;

public enum PLAYER implements PlayerOperations{

	
	PLAYERONE("player_1",new PlayerONEOperationsImplement() ),PLAYERTWO("player_2",new PlayerONEOperationsImplement() ),PLAYERTHREE("player_3",new PlayerONEOperationsImplement() ),PLAYEFOUR("player_4",new PlayerONEOperationsImplement() );
	
	private String playerId;
	PlayerOperations operations;
	PLAYER(String playerId,PlayerOperations operations ){
		this.setPlayerId(playerId);
		this.operations = operations;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public void removeCard(int cardNum) {
		// TODO Auto-generated method stub
		operations.removeCard(cardNum);
	}

	public void assignCards(CARD[] cards, int position) {
		// TODO Auto-generated method stub
		operations.assignCards(cards, position);
	}

	public void setUserId(String userId) {
		operations.setUserId(userId);
	}

	public String getUserId() {
		// TODO Auto-generated method stub
		return operations.getUserId();
	}

	public void setCard1(CARD card) {
		operations.setCard1(card);		
	}

	public void setCard2(CARD card) {
		// TODO Auto-generated method stub
		operations.setCard2(card);
	}

	public void setCard3(CARD card) {
		// TODO Auto-generated method stub
		operations.setCard3(card);
	}

	public void setMyCard(CARD card) {
		// TODO Auto-generated method stub
		operations.setMyCard(card);
	}

	public void setShowHand(boolean showHand) {
		// TODO Auto-generated method stub
		operations.setShowHand(showHand);
	}

	public void incrementPoint() {
		// TODO Auto-generated method stub
		operations.incrementPoint();
	}

	public int getPoint() {
		// TODO Auto-generated method stub
		return operations.getPoint();
	}

	public void setMessage(String message) {
		operations.setMessage(message);
	}

	

	public Response handleSSERequest(HttpServletRequest req, PLAYER playerContex) {
		// TODO Auto-generated method stub
		System.out.println("inside player");
		return operations.handleSSERequest(req, playerContex);
	}

	public Message generateStatusMessage(boolean initial) {
		// TODO Auto-generated method stub
		return operations.generateStatusMessage(initial);
	}

	public void setShowCards(boolean showCards) {
		// TODO Auto-generated method stub
		operations.setShowCards(showCards);
	}
	
	
}
