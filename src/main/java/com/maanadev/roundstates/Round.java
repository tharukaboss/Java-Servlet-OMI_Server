package com.maanadev.roundstates;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.maanadev.cards.CARD;
import com.maanadev.messages.Response;
import com.maanadev.player.PLAYER;

public enum Round implements RoundstateOperations{

	ROUNDINITIAL(new RoundInitial()), ROUND_1(new RoundMiddle()), ROUND_2(new RoundMiddle()), ROUND_3(
			new RoundMiddle()), ROUND_4(new RoundMiddle()), ROUND_5(new RoundMiddle()), ROUND_6(
					new RoundMiddle()), ROUND_7(new RoundMiddle()), ROUND_8(new RoundMiddle()), ROUND_9(
							new RoundMiddle()), ROUND_10(new RoundMiddle()),ROUND_11(new RoundMiddle()),ROUND_12(new RoundMiddle()),ROUNDFinal(new RoundMiddle());
	RoundstateOperations operations;

	Round(RoundstateOperations operations) {
		this.operations = operations;
	}

	public Response handleSSERequest(HttpServletRequest req, RoundstateOperations context) {
		return operations.handleSSERequest(req, context);
		
	}

	public void handlePostRequest(HttpServletRequest req, RoundstateOperations context) {
		// TODO Auto-generated method stub
		
	}

	public void assignFirstHand(CARD[] cards, int position) {
		operations.assignFirstHand(cards, position);
		
	}

	public void setTrumph(CARD trumph) {
		operations.setTrumph(trumph);
	}

	public CARD getTrumph() {
		// TODO Auto-generated method stub
		return operations.getTrumph();
	}

	public void addPlayer(String userId, PLAYER player) {
		// TODO Auto-generated method stub
		operations.addPlayer(userId, player);
	}

	public void setPlayers(HashMap<String, PLAYER> players) {
		// TODO Auto-generated method stub
		operations.setPlayers(players);
	}

	public PLAYER getPlayer(String userId) {
		// TODO Auto-generated method stub
		return operations.getPlayer(userId);
	}

	public void setPlayerContext(PLAYER player) {
		// TODO Auto-generated method stub
		operations.setPlayerContext(player);
	}

	public PLAYER getPlayerContext() {
		// TODO Auto-generated method stub
		return operations.getPlayerContext();
	}

	public void generateStatusMessages() {
		// TODO Auto-generated method stub
		operations.generateStatusMessages();
	}

	public void generateStatusmessage(String userId) {
		// TODO Auto-generated method stub
		operations.generateStatusmessage(userId);
	}

	public PLAYER nextPlayer() {
		// TODO Auto-generated method stub
		return operations.nextPlayer();
	}

	public Round nextRound() {
		// TODO Auto-generated method stub
		return operations.nextRound();
	}

	public void incrementConnectedPlayerCount() {
		// TODO Auto-generated method stub
		operations.incrementConnectedPlayerCount();
	}

	public void setPlayerNumToUserId(HashMap<Integer, String> playerNumToUserId) {
		// TODO Auto-generated method stub
		operations.setPlayerNumToUserId(playerNumToUserId);
	}

	public void setmessage(String userId) {
		// TODO Auto-generated method stub
		operations.setmessage(userId);
	}

	public synchronized int getConnectedPlayerCount() {
		return operations.getConnectedPlayerCount();
	}

	
}
