package com.maanadev.roundstates;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.bidimap.DualHashBidiMap;

import com.maanadev.cards.CARD;
import com.maanadev.cards.SUIT;
import com.maanadev.messages.PlayerCardChangeReq;
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
	private SUIT suit;
	private HashMap<String, CARD> winnerTrack = new HashMap<String, CARD>();
	private int connectedPlayerCount = 0;
	
	public Response handleSSERequest(HttpServletRequest req, RoundstateOperations context) {
		if (!roundstarted) {
			CardHandler cardHandler = new CardHandler();
			cardHandler.init();
			setTrumph(cardHandler.getTrumph());
			assignFirstHand(cardHandler.getCardsHands(), 0);
			setmessage(playerContext.getUserId());
			roundstarted=true;
		}  
		String userId = req.getHeader("Last-Event-ID");
		return getPlayer(userId).handleSSERequest(req, playerContext);

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

	public   void incrementConnectedPlayerCount() {
			
		connectedPlayerCount++;
			
	}

	public void setPlayerNumToUserId(DualHashBidiMap playerNumToUserId) {
		this.playerNumToUserId = playerNumToUserId;
	}

	public void setmessage(String userId) {
		getPlayer(userId).setMessage(PLAYMESSAGE);
	}

	public int getConnectedPlayerCount() {
		// TODO Auto-generated method stub
		return connectedPlayerCount;
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

	public int getPlayerIdNum(String userid) {
		int playerNum;

		synchronized (playerNumToUserId) {
			playerNum = (Integer) playerNumToUserId.getKey(userid);
		}

		return playerNum;
	}

	public CARD getCardFromReq(PlayerCardChangeReq req) {
		int cardNum = Integer.parseInt(req.getCard().split("/")[1].split("\\.")[0]);
		return playerContext.getCard(cardNum);
	}

	private void addCurrentCard(String userId,CARD card){
		winnerTrack.put(userId, card);
	}
	public Response handlePostRequest(PlayerCardChangeReq req) {
		
		boolean handisvalid =false;
		System.out.println("post "+req.getUserId()); 
		if (req.getUserId().equals(playerContext.getUserId())) {//check whether correct player is playing
		
			CARD card = getCardFromReq(req);
			
			if (getConnectedPlayerCount() == 0) {//is trick starting
					System.out.println("initial");
				suit = card.getSuit();
				addCurrentCard(req.getUserId(), card);
				updateHand(req.getUserId(),card);
				incrementConnectedPlayerCount();// store how many players played
				playerContext = nextPlayer();	
				handisvalid =true;
			} else{
				
				if(card.getSuit()==suit){
					System.out.println("card suit");
					addCurrentCard(req.getUserId(), card);
					updateHand(req.getUserId(),card);
					incrementConnectedPlayerCount();// store how many players played
					playerContext = nextPlayer();	
					handisvalid=true;
				}else{
					
					if(playerContext.isCardThere(suit)){
						System.out.println("card is in");
					}else{
						System.out.println("card is not");
						addCurrentCard(req.getUserId(), card);
						updateHand(req.getUserId(),card);
						incrementConnectedPlayerCount();// store how many players played
						playerContext = nextPlayer();
						handisvalid =true;
					}
				}
			}
			
			if(handisvalid && (getConnectedPlayerCount()==4)){
				String winner=null;
				CARD tmpcardPre=null ;
				for(String user:winnerTrack.keySet()){
					CARD tmpcard = winnerTrack.get(user);
					
					
					if(tmpcard.getSuit()==suit){
						if(winner==null){
							 tmpcardPre = winnerTrack.get(user);
							winner =user;
						}else{
							if(tmpcard.getcardVaule()>tmpcardPre.getcardVaule()){
								winner=user;
								tmpcardPre=tmpcard;
							}
						}
					}else{
						if(tmpcard.getSuit()==trumph.getSuit() ){
							if(tmpcardPre.getSuit()==trumph.getSuit()){
								if(tmpcard.getcardVaule()>tmpcardPre.getcardVaule()){
									winner=user;
									tmpcardPre=tmpcard;
								}
							}else{
								winner=user;
								tmpcardPre=tmpcard;
							}
						}
					}
				}
				PLAYER winnerPlayer =getPlayer(winner);
				winnerPlayer.incrementPoint();
				winnerPlayer.setMessage("You win");
				
			}
			
			
		
		return null;
	} else {
		return null;
	}
	}


	public void updateHand(String userId, CARD card) {
		int playerNum = getPlayerIdNum(userId);

		// set mycard
		playerContext.setMyCard(card);
		playerContext.removeCard(card.getcardVaule());
		//updating the card for each player
		for (int i = 0; i < 3; i++) {

			playerNum++;
			playerNum %= 4;
			System.out.println(playerNum); 
			PLAYER player;
			synchronized (playerNumToUserId) {
				player = getPlayer((String) playerNumToUserId.get(playerNum));
			}
			
			switch (i) {
			case 0:
				
				player.setCard1(card);
				break;

				
			case 1: 
				player.setCard2(card);
				break;
				
			case 2: 
				player.setCard3(card);
				break;
			default:
				break;
			}
		}
		
	}
}
