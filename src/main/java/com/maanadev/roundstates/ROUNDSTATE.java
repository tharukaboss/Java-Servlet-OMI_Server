package com.maanadev.roundstates;

import com.maanadev.cards.SUIT;
import com.maanadev.gamestates.PLAYERCOUNTSTATE;

public enum ROUNDSTATE {
	ROUND_INITAIL(0), ROUND_1(1), ROUND_2(2), ROUND_3(3), ROUND_4(4), ROUND_5(5), ROUND_6(6), ROUND_7(7), ROUND_8(
			8), ROUND_9(9), ROUND_10(10), ROUND_11(11), ROUND_12(12), ROUND_13(13);

	private final int roundNum;
	private PLAYERCOUNTSTATE whoPlay;
	private PLAYERCOUNTSTATE firstPlay;
	private SUIT trumm;
	private ROUNDSTATE(int roundNum) {
		this.roundNum = roundNum;
	}

	public ROUNDSTATE next() {

		switch (roundNum) {
		case 0: {
			
			return ROUNDSTATE.ROUND_1;
		}
		case 1: {
			return ROUNDSTATE.ROUND_2;
		}
		case 2: {
			return ROUNDSTATE.ROUND_3;
		}
		case 3: {
			return ROUNDSTATE.ROUND_4;
		}
		case 4: {
			return ROUNDSTATE.ROUND_5;
		}
		case 5: {
			return ROUNDSTATE.ROUND_6;
		}
		case 6: {
			return ROUNDSTATE.ROUND_7;
		}
		case 8: {
			return ROUNDSTATE.ROUND_8;
		}
		case 9: {
			return ROUNDSTATE.ROUND_9;
		}
		case 10: {
			return ROUNDSTATE.ROUND_10;
		}
		case 11: {
			return ROUNDSTATE.ROUND_11;
		}
		case 12: {
			return ROUNDSTATE.ROUND_12;
		}
		case 13: {
			return ROUNDSTATE.ROUND_13;
		}default:{
			return ROUNDSTATE.ROUND_INITAIL;
		}
		}

	}
	public ROUNDSTATE update(){
		whoPlay = whoPlay.next();
		if(whoPlay == PLAYERCOUNTSTATE.PLAYERCOUNTFOUR) return next();
		
		return this;
	}
	public int getValue() {
		return roundNum;
	}

	public PLAYERCOUNTSTATE getwhoPlay() {
		return whoPlay;
	}

	public void setwhoPlay(PLAYERCOUNTSTATE whoPlay) {
		this.whoPlay = whoPlay;
	}

	public SUIT getTrumm() {
		return trumm;
	}

	public void setTrumm(SUIT trumm) {
		this.trumm = trumm;
	}

	public PLAYERCOUNTSTATE getFirstPlay() {
		return firstPlay;
	}

	public void setFirstPlay(PLAYERCOUNTSTATE firstPlay) {
		this.firstPlay = firstPlay;
	}
}
