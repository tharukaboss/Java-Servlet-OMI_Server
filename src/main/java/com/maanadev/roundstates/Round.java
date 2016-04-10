package com.maanadev.roundstates;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.bidimap.DualHashBidiMap;

import com.maanadev.cards.CARD;
import com.maanadev.messages.Response;
import com.maanadev.player.PLAYER;

public enum Round implements RoundstateOperations {

	ROUNDINITIAL(new RoundInitial(), 0), ROUND_1(new RoundMiddle(), 1), ROUND_2(new RoundMiddle(),
			2), ROUND_3(new RoundMiddle(), 3), ROUND_4(new RoundMiddle(), 4), ROUND_5(new RoundMiddle(), 5), ROUND_6(
					new RoundMiddle(), 6), ROUND_7(new RoundMiddle(), 7), ROUND_8(new RoundMiddle(), 8), ROUND_9(
							new RoundMiddle(), 9), ROUND_10(new RoundMiddle(), 10), ROUND_11(new RoundMiddle(),
									11), ROUND_12(new RoundMiddle(), 13), ROUNDFinal(new RoundMiddle(), 13);
	RoundstateOperations operations;
	private int roundNum;

	Round(RoundstateOperations operations, int roundNum) {
		this.operations = operations;
		this.roundNum = roundNum;
	}

	public Response handleSSERequest(HttpServletRequest req, RoundstateOperations context) {
		return operations.handleSSERequest(req, context);

	}

	public Response handlePostRequest(HttpServletRequest req, RoundstateOperations context) {
		return operations.handlePostRequest(req, context);
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

		switch (this.roundNum) {
		case 0:{
			Round round =Round.ROUND_1;
			operations.setUpRound(round);
			return round;
		}
		case 1:{
			Round round =Round.ROUND_2;
			operations.setUpRound(round);
			return round;
		}
		case 2:{
			Round round =Round.ROUND_3;
			operations.setUpRound(round);
			return round;
		}
		case 3:{
			Round round =Round.ROUND_4;
			operations.setUpRound(round);
			return round;
		}
		case 4:{
			Round round =Round.ROUND_5;
			operations.setUpRound(round);
			return round;
		}
		case 5:{
			Round round =Round.ROUND_6;
			operations.setUpRound(round);
			return round;
		}

		case 6:{
			Round round =Round.ROUND_7;
			operations.setUpRound(round);
			return round;
		}
		case 7:{
			Round round =Round.ROUND_8;
			operations.setUpRound(round);
			return round;
		}
		case 8:{
			Round round =Round.ROUND_9;
			operations.setUpRound(round);
			return round;
		}
		case 9:{
			Round round =Round.ROUND_10;
			operations.setUpRound(round);
			return round;
		}
		case 10:{
			Round round =Round.ROUND_11;
			operations.setUpRound(round);
			return round;
		}
		case 11:{
			Round round =Round.ROUND_12;
			operations.setUpRound(round);
			return round;
		}
		case 12:
		{
			Round round =Round.ROUNDINITIAL;
			operations.setUpRound(round);
			return round;
		}
		default:
			return null;
		}
		
	}

	public void incrementConnectedPlayerCount() {
		// TODO Auto-generated method stub
		operations.incrementConnectedPlayerCount();
	}

	public void setPlayerNumToUserId(DualHashBidiMap playerNumToUserId) {
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

	public void setUpRound(Round roundContext) {
		operations.setUpRound(roundContext);
	}

}
