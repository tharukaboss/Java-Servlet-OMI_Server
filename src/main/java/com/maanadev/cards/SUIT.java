package com.maanadev.cards;

public enum SUIT implements CardContext {

	DIAMONDS {

		public String getName() {
			return "DAIMONDS";
		}

		public int getcardVaule() {
			return DIAMONDVALUE;
		}

	},
	HEARTS {

		public String getName() {
			return "HEARTS";
		}

		public int getcardVaule() {
			return HEARTSVALUE;
		}

	},
	SPADES {

		public String getName() {
			return "SPADES";
		}

		public int getcardVaule() {
			return SPADESVALUE;
		}

	},
	CLUBS {

		public String getName() {
			return "CLUBS";
		}

		public int getcardVaule() {
			return CLUBSVALUE;
		}

	}
}
