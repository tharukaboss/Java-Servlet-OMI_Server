package com.maanadev.player;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.maanadev.cards.CARD;
import com.maanadev.messages.Message;
import com.maanadev.messages.Response;

public interface PlayerOperations {

	public void removeCard(int cardNum);

	public void assignCards(CARD cards[], int position);

	public void setUserId(String userId);

	public String getUserId();

	public void setCard1(CARD card);

	public void setCard2(CARD card);

	public void setCard3(CARD card);

	public void setMyCard(CARD card);

	public void setShowHand(boolean showHand);

	public void incrementPoint();

	public int getPoint();
	
	public void setMessage(String message);
	
	public Response handleSSERequest(HttpServletRequest req, PLAYER playerContex);
	
	public Message generateStatusMessage(boolean intitial);
	
	public void setShowCards(boolean showCards);
	
}
