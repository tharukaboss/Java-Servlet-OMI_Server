package com.maanadev.player;

import java.util.HashMap;

import com.maanadev.cards.CARD;

public class PlayerSuper {

	HashMap<Integer, CARD> hand = new HashMap<Integer, CARD>();

	protected String userId=null;
	protected CARD card1=null;
	protected CARD card2=null;
	protected CARD card3=null;
	protected CARD myCard=null;
	protected boolean showHand;
	protected boolean showCards;
	protected int points = 0;
	protected String message=null;
	public PlayerSuper() {
	}
}
