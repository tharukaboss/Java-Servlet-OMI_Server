package com.maanadev.omiserver;

import javax.servlet.http.HttpServletRequest;

import com.maanadev.messages.PlayReq;
import com.maanadev.messages.PlayerCardChangeReq;
import com.maanadev.messages.Response;
import com.maanadev.roundstates.Round;
import com.maanadev.roundstates.RoundstateOperations;

public class GameMain {

	Round roundContext;

	public GameMain() {
		roundContext = Round.ROUNDINITIAL;
	}

	public Response handleSSERequest(HttpServletRequest req) {
		/// System.out.println(roundContext.getConnectedPlayerCount());
		if (roundContext.getConnectedPlayerCount() == 4 && roundContext==Round.ROUNDINITIAL) {

			roundContext = roundContext.nextRound();

			// System.out.println("round changed");
		}

		return roundContext.handleSSERequest(req, roundContext);
	}

	public Response handlePOSTRequst(PlayerCardChangeReq cardChangeReq) {
			System.out.println("ok req");
		return roundContext.handlePostRequest(cardChangeReq);
	}


}
