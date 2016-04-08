package com.maanadev.cards;

public interface CardContext {
	public static final String CARDS_ = "cards/";

	public static final String DIAMONDNAME = "0_";
	public static final String HEARTSNAME = "2_";
	public static final String SPADESNAME = "3_";
	public static final String CLUBSNAME = "1_";

	public static final int DIAMONDVALUE = 0;
	public static final int HEARTSVALUE = 26;
	public static final int CLUBSVALUE = 13;
	public static final int SPADESVALUE = 39;
	// CARD VALUES
	public static final int CARD_A = 1;
	public static final int CARD_2 = 2;
	public static final int CARD_3 = 3;
	public static final int CARD_4 = 4;
	public static final int CARD_5 = 5;
	public static final int CARD_6 = 6;
	public static final int CARD_7 = 7;
	public static final int CARD_8 = 8;
	public static final int CARD_9 = 9;
	public static final int CARD_10 = 10;
	public static final int CARD_J = 11;
	public static final int CARD_Q = 12;
	public static final int CARD_K = 13;

	// METHODS
	public String getName();
	public int getcardVaule();
}
