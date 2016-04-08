package com.maanadev.omiserver;

import java.util.HashMap;

import com.maanadev.cards.CARD;
import com.maanadev.gamestates.GAMESTATE;
import com.maanadev.gamestates.PLAYERCOUNTSTATE;
import com.maanadev.roundstates.ROUNDSTATE;

public class Game {

	private ROUNDSTATE roundstate = ROUNDSTATE.ROUND_INITAIL;
	private GAMESTATE gamestate;
	private PLAYERCOUNTSTATE playercountstate = PLAYERCOUNTSTATE.PLAYERCOUNTZERO;

	// players
	protected PLAYER player_1;
	protected PLAYER player_2;
	protected PLAYER player_3;
	protected PLAYER player_4;

	private CardHandler cardHandler;

	private void updatePlayerCountState() {
		synchronized (playercountstate) {
			playercountstate = playercountstate.next();
			System.out.println("player count is updated :"+playercountstate.getPlayerNum());
		}
	}

	protected void updateRoundState() {
		synchronized (roundstate) {
			roundstate = roundstate.next();
			System.out.println("Round is updated: "+ roundstate.getValue());
		}
		
	}

	public Game() {
	}

	public ROUNDSTATE getRoundstate() {
		synchronized (roundstate) {
			return roundstate;
		}

	}

	public void setRoundstate(ROUNDSTATE roundstate) {
		this.roundstate = roundstate;
	}

	public GAMESTATE getGamestate() {
		return gamestate;
	}

	public void setGamestate(GAMESTATE gamestate) {
		this.gamestate = gamestate;
	}

	public PLAYERCOUNTSTATE getPlayercountstate() {
		return playercountstate;
	}

	public void setPlayercountstate(PLAYERCOUNTSTATE playerstate) {
		this.playercountstate = playerstate;
	}

	public void init() {

	}

	public void createPlayer(String userId) {
		if (playercountstate == PLAYERCOUNTSTATE.PLAYERCOUNTZERO) {
			player_1 = PLAYER.PLAYERONE;
			player_1.setUserID(userId);
			updatePlayerCountState();
		} else if (playercountstate == PLAYERCOUNTSTATE.PLAYERCOUNTONE) {
			player_2 = PLAYER.PLAYERTWO;
			player_2.setUserID(userId);
			updatePlayerCountState();
		} else if (playercountstate == PLAYERCOUNTSTATE.PLAYERCOUNTTWO) {
			player_3 = PLAYER.PLAYERTHREE;
			player_3.setUserID(userId);
			updatePlayerCountState();
		} else if (playercountstate == PLAYERCOUNTSTATE.PLAYERCOUNTTHREE) {
			player_4 = PLAYER.PLAYERFOUR;
			player_4.setUserID(userId);
			updatePlayerCountState();
			assingCards();

		}
	}

	public void start() {
		roundstate = roundstate.next();
	}

	public CardHandler getCardHandler() {
		return cardHandler;
	}

	public void setCardHandler(CardHandler cardHandler) {
		this.cardHandler = cardHandler;
	}

	protected void assingCards() {

		CARD[] cards = cardHandler.getCardsHands();

		for (int i = 0; i < 13; i++) {
			CARD card = cards[i];
			player_1.put(card.getcardVaule(), card);
		}

		for (int i = 13; i < 26; i++) {
			CARD card = cards[i];
			player_2.put(card.getcardVaule(), card);
		}
		for (int i = 26; i < 39; i++) {
			CARD card = cards[i];
			player_3.put(card.getcardVaule(), card);
		}
		for (int i = 39; i < 52; i++) {
			CARD card = cards[i];
			player_4.put(card.getcardVaule(), card);
		}
		roundstate.setTrumm(cards[51].getSuit());
	}

	public synchronized void sendCardHandToAll() {

	}

}
