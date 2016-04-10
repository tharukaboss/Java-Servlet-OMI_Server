package com.maanadev.roundstates;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.bidimap.DualHashBidiMap;

import com.maanadev.cards.CARD;
import com.maanadev.messages.Response;
import com.maanadev.player.PLAYER;

public class RoundInitial implements RoundstateOperations {

	private CARD trumph;
	private int conntctedPlayerCount = 0;
	private int id = 1111;
	private String nxtPlayerContext;
	private static final String WAITINGMESSAGE = "Waiting ..... No of players connected: ";
	private HashMap<String, PLAYER> players = new HashMap<String, PLAYER>();
	private DualHashBidiMap playerNumToUserId = new DualHashBidiMap();

	public Response handleSSERequest(HttpServletRequest req, RoundstateOperations context) {

		if (req.getHeader("Last-Event-ID") == null) {
			System.out.println("don't have ID");
			switch (conntctedPlayerCount) {
			case 0: {
				System.out.println("creating player 1");
				PLAYER player = PLAYER.PLAYERONE;
				// set message

				// setuserId
				String userId = id + "";
				nxtPlayerContext = userId;
				playerNumToUserId.put(0, userId);
				id++;
				/// look here
				incrementConnectedPlayerCount();
				player.setUserId(userId);
				player.setMessage(WAITINGMESSAGE + conntctedPlayerCount);
				addPlayer(userId, player);

				Response res = new Response();
				res.setUserId(userId);
				System.out.println("round :" + userId);
				res.setMessage(player.generateStatusMessage(true));
				return res;
			}
			case 1: {
				System.out.println("creating player 2");
				PLAYER player = PLAYER.PLAYERTWO;
				// set message

				String userId = id + "";
				playerNumToUserId.put(1, userId);
				id++;
				incrementConnectedPlayerCount();
				player.setUserId(userId);
				player.setMessage(WAITINGMESSAGE + conntctedPlayerCount);
				addPlayer(userId, player);

				Response res = new Response();
				res.setUserId(userId);
				res.setMessage(player.generateStatusMessage(true));
				return res;
			}
			case 2: {
				PLAYER player = PLAYER.PLAYERTHREE;
				// set message
				String userId = id + "";
				playerNumToUserId.put(2, userId);
				id++;
				incrementConnectedPlayerCount();
				player.setUserId(userId);
				player.setMessage(WAITINGMESSAGE + conntctedPlayerCount);
				addPlayer(userId, player);

				Response res = new Response();
				res.setUserId(userId);
				res.setMessage(player.generateStatusMessage(true));
				return res;

			}
			case 3: {
				System.out.println("creating player 4");
				PLAYER player = PLAYER.PLAYEFOUR;
				// set message
				String userId = id + "";
				playerNumToUserId.put(3, userId);
				id++;
				incrementConnectedPlayerCount();
				player.setUserId(userId);
				player.setMessage(WAITINGMESSAGE + conntctedPlayerCount);
				addPlayer(userId, player);

				Response res = new Response();
				res.setUserId(userId);
				res.setMessage(player.generateStatusMessage(true));
				return res;
			}

			default:
				return null;
			}

		} else {
			System.out.println("have ID");
			/// already registered
			String userId = req.getHeader("Last-Event-ID");
			System.out.println(userId);
			/// hash map save userId
			return getPlayer(userId).handleSSERequest(req, null);

		}
	}

	public Response handlePostRequest(HttpServletRequest req, RoundstateOperations context) {
			return null;
	}

	public void assignFirstHand(CARD[] cards, int position) {

	}

	public void setTrumph(CARD trumph) {
		this.trumph = trumph;
	}

	public CARD getTrumph() {
		return trumph;
	}

	public void addPlayer(String userId, PLAYER player) {
		synchronized (players) {
			players.put(userId, player);
		}
	}

	public void setPlayers(HashMap<String, PLAYER> players) {

	}

	public PLAYER getPlayer(String userId) {
		synchronized (players) {
			return players.get(userId);
		}
	}

	public void setPlayerContext(PLAYER player) {

	}

	public PLAYER getPlayerContext() {
		return null;
	}

	public void generateStatusMessages() {

	}

	public void generateStatusmessage(String userId) {

	}

	public PLAYER nextPlayer() {

		return null;
	}

	public Round nextRound() {
		return null;
	}

	public synchronized void incrementConnectedPlayerCount() {
		conntctedPlayerCount++;
	}

	public void setPlayerNumToUserId(DualHashBidiMap playerNumToUserId) {

	}

	public int getConnectedPlayerCount() {
		return conntctedPlayerCount;
	}

	public void setmessage(String userId) {
		getPlayer(userId).setMessage(WAITINGMESSAGE + conntctedPlayerCount);

	}

	public void setUpRound(Round roundContext) {
		roundContext.setPlayers(players);
		roundContext.setPlayerNumToUserId(playerNumToUserId);
		roundContext.setPlayerContext(getPlayer(nxtPlayerContext));
		
	}

	

}
