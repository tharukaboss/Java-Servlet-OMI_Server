package com.maanadev.omiserver;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.maanadev.cards.CARD;

public class CardHandler {

	private static HashMap<Integer, CARD> cards = new HashMap<Integer, CARD>();
	private int [] cardsArray = new int[52];
	public CardHandler() {
	}

	public void init() {

		EnumSet<CARD> enumset = EnumSet.allOf(CARD.class);
		ArrayList<CARD> list = new ArrayList<CARD>(enumset);
		
		for (CARD card : list) {
			int key = card.getcardVaule()+card.getSuit().getcardVaule();
			cards.put(key, card);
		

		}
		createArray();
		shuffleArray();
	}

	public CARD getCard(int value) {

		synchronized (cards) {
			return cards.get(value);
		}
	}
	private void createArray(){
		for (int i =0; i < 52; i++) {
			cardsArray[i]=(i+1);
		}
	}
	private void shuffleArray() {
		Random rnd = ThreadLocalRandom.current();
		for (int i = cardsArray.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			int a = cardsArray[index];
			cardsArray[index] = cardsArray[i];
			cardsArray[i] = a;
		}
	}
	public CARD [] getCardsHands(){
		createArray();
		shuffleArray();
		CARD array[]= new CARD[52];
		int count = 0 ;
		for (int value : cardsArray) {
			array[count] = getCard(value);
			count++;
		}
		return array;
	}
	
		public CARD getTrumph(){
			
			return getCard(cardsArray[51]);
		}
	
}
