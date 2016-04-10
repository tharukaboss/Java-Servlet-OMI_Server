package com.maanadev.roundstates;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.bidimap.DualHashBidiMap;

import com.maanadev.cards.CARD;
import com.maanadev.messages.Response;
import com.maanadev.omiserver.CardHandler;
import com.maanadev.player.PLAYER;

public class RoundMiddle implements RoundstateOperations {

	private boolean roundstarted = false;
	private CARD trumph;
	private PLAYER playerContext;
	private HashMap<String, PLAYER> players;
	private DualHashBidiMap playerNumToUserId;
	private static final String PLAYMESSAGE = "play!!!";

	public Response handleSSERequest(HttpServletRequest req, RoundstateOperations context) {
		System.out.println("round changeed ok");
		if (!roundstarted) {
			CardHandler cardHandler = new CardHandler();
			cardHandler.init();

			assignFirstHand(cardHandler.getCardsHands(), 0);
			setmessage(playerContext.getUserId());
			roundstarted=true;
		}  
		String userId = req.getHeader("Last-Event-ID");
		return getPlayer(userId).handleSSERequest(req, playerContext);

	}

	public Response handlePostRequest(HttpServletRequest req, RoundstateOperations context) {
			return playerContext.handleSSERequest(req,null);
	}

	public void assignFirstHand(CARD[] cards, int position) {
		int pos = 0;
		for (int i = 0; i < 4; i++) {
			synchronized (players) {
				PLAYER player = players.get(playerNumToUserId.get(i));
				player.assignCards(cards, pos);
				player.setShowHand(true);
				player.setShowCards(true);
				player.setMessage("Ready !");
				
				pos = +13;
			}
		}
		playerContext.setMessage("Play!!");
		
		setTrumph(cards[51]);
	}

	public void setTrumph(CARD trumph) {
		this.trumph = trumph;
	}

	public CARD getTrumph() {
		// TODO Auto-generated method stub
		return trumph;
	}

	public void addPlayer(String userId, PLAYER player) {
		synchronized (players) {
			players.put(userId, player);
		}
	}

	public void setPlayers(HashMap<String, PLAYER> players) {
		this.players = players;
	}

	public PLAYER getPlayer(String userId) {
		// TODO Auto-generated method stub
		synchronized (players) {
			return players.get(userId);
		}
	}

	public void setPlayerContext(PLAYER player) {
		this.playerContext = player;
	}

	public PLAYER getPlayerContext() {
		// TODO Auto-generated method stub
		return playerContext;
	}

	public void generateStatusMessages() {

	}

	public void generateStatusmessage(String userId) {
		// TODO Auto-generated method stub

	}

	public PLAYER nextPlayerForNxtRound() {
		int playerNum;
		synchronized (playerNumToUserId) {
			 playerNum = (Integer) playerNumToUserId.getKey(playerContext.getUserId());
			 playerNum = (playerNum+2 )%4;
				
			 return getPlayer((String)playerNumToUserId.get(playerNum));
		}
		
	}

	public Round nextRound() {
		///this is implemented in the Enum
		return null;
	}

	public void incrementConnectedPlayerCount() {
		// TODO Auto-generated method stub

	}

	public void setPlayerNumToUserId(DualHashBidiMap playerNumToUserId) {
		this.playerNumToUserId = playerNumToUserId;
	}

	public void setmessage(String userId) {
		getPlayer(userId).setMessage(PLAYMESSAGE);
	}

	public int getConnectedPlayerCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setUpRound(Round roundContext) {
		roundContext.setPlayers(players);
		roundContext.setPlayerNumToUserId(playerNumToUserId);
		roundContext.setPlayerContext(nextPlayerForNxtRound());
	}

	public PLAYER nextPlayer() {
		int playerNum;
		synchronized (playerNumToUserId) {
			 playerNum = (Integer) playerNumToUserId.getKey(playerContext.getUserId());
			 playerNum = (playerNum+1 )%4;
				
			 return getPlayer((String)playerNumToUserId.get(playerNum));
		}
		
	}

}
