package com.maanadev.roundstates;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.bidimap.DualHashBidiMap;

import com.maanadev.cards.CARD;
import com.maanadev.messages.Response;
import com.maanadev.player.PLAYER;

public interface RoundstateOperations {

	
	public Response handleSSERequest(HttpServletRequest req,RoundstateOperations context);
	public Response handlePostRequest(HttpServletRequest req,RoundstateOperations context);
	public void assignFirstHand(CARD cards[],int position);
	public void setTrumph(CARD trumph);
	public CARD getTrumph();
	public void addPlayer(String userId,PLAYER player);
	public void setPlayers(HashMap<String, PLAYER> players);
	public PLAYER getPlayer(String userId);
	public void setPlayerContext(PLAYER player);
	public PLAYER getPlayerContext();
	public void generateStatusMessages();
	public void generateStatusmessage(String userId);
	public PLAYER nextPlayerForNxtRound();
	public PLAYER nextPlayer();
	public Round nextRound();
	public void incrementConnectedPlayerCount();
	public void setPlayerNumToUserId(DualHashBidiMap playerNumToUserId);
	public void setmessage(String userId);
	public int getConnectedPlayerCount();
	public void setUpRound(Round roundContext);
}
