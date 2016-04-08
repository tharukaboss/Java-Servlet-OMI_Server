package com.maanadev.omiserver;

import javax.servlet.http.HttpServletRequest;

import com.maanadev.messages.Message;

public class LogicHandler extends Game{
	int id=0;
	public LogicHandler() {
		super();
	}
	
	public void handleSSERequest(HttpServletRequest req){
	
	}
	public void handlePostRequest(HttpServletRequest req){
		
	}
	private void startGame() {
		
	}

	private Message createWaitingMessage() {
		Message m = new Message();

		m.setMessage("Waiting .... Only " + getPlayercountstate().getPlayerNum() + " Connected !.");
		m.setShowCards(false);
		m.setShowHand(false);

		m.setCards(null);
		
		return m;
	}
	private PLAYER getPlayer(String userId){
		
		if(player_1.getUserID().equals(userId)) return player_1;
		else if(player_2.getUserID().equals(userId) ) return player_2;
		else if(player_3.getUserID().equals(userId))return player_3;
		else if(player_4.getUserID().equals(userId))return player_4;
		else return null;
	}
}
