package com.maanadev.omiserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maanadev.constants.Constants;
import com.maanadev.gamestates.PLAYERCOUNTSTATE;
import com.maanadev.messages.ImageBind;
import com.maanadev.messages.Message;

public class Gateway extends HttpServlet implements Constants {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	LogicHandler game = new LogicHandler();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("receied");

		// resp.setContentType(CONTEXT_TYPE);
		// resp.setCharacterEncoding("UTF-8");
		// PrintWriter writter = resp.getWriter();
		// writter.write(getMessageString(m));
		// writter.close();
		// if(game.getPlayercountstate()!=PLAYERCOUNTSTATE.PLAYERCOUNTFOUR){
		// game.createPlayer();
		// ///send waiting message
		// watingForOtherPlayers();
		//
		// }
		resp.setContentType(CONTEXT_TYPE);
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writter = resp.getWriter();

		System.out.println(req.getHeader("Last-Event-ID"));

		if (req.getHeader("Last-Event-ID") == null) {
			game.createPlayer("1531");
			sendSSE(writter, createWaitingMessage());

		} else {

		}

	}

	private Message createWaitingMessage() {
		Message m = new Message();

		m.setMessage("Waiting .... Only " + game.getPlayercountstate().getPlayerNum() + " Connected !.");
		m.setShowCards(false);
		m.setShowHand(false);

		m.setCards(null);

		return m;
	}

	
	private String getMessageString(Message m) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		try {
			jsonInString = mapper.writeValueAsString(m);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

	private void sendSSE(PrintWriter writer, Message m) {

		writer.print("id: 1531\n");
		writer.print("data: " + getMessageString(m) + "\n\n");
		writer.print("data: 1531\n\n");
		writer.flush();
		writer.close();
	}

}
