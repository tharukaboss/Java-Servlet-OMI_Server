package com.maanadev.gamestates;

public enum PLAYERCOUNTSTATE {

	PLAYERCOUNTZERO(0), PLAYERCOUNTONE(1), PLAYERCOUNTTWO(2), PLAYERCOUNTTHREE(3), PLAYERCOUNTFOUR(4);

	private final int playerNum;
	private String id;
	PLAYERCOUNTSTATE(int playerNum) {

		this.playerNum = playerNum;
	}

	public int getPlayerNum() {
		return playerNum;
	}

	public PLAYERCOUNTSTATE next() {
		switch (playerNum) {
		case 0:
			return PLAYERCOUNTSTATE.PLAYERCOUNTONE;
		case 1:
			return PLAYERCOUNTSTATE.PLAYERCOUNTTWO;
		case 2:
			return PLAYERCOUNTSTATE.PLAYERCOUNTTHREE;
		case 3:
			return PLAYERCOUNTSTATE.PLAYERCOUNTFOUR;
		default:
			return null;

		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
