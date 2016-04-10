package com.maanadev.omiserver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maanadev.messages.Message;
import com.maanadev.messages.Response;

public class Gateway extends HttpServlet {

	GameMain game;
	@Override
	public void init() throws ServletException {
		game= new GameMain();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/event-stream");
		resp.setCharacterEncoding("UTF-8");
		System.out.println("req");
		
		sendSSE(resp.getWriter(), 	game.handleSSERequest(req));
		
	}
	private void sendSSE(PrintWriter writer,Response res){
		System.out.println(res.getUserId());
		writer.print("id:"+res.getUserId()+"\n");
		writer.print("data:"+getJSON_Message(res.getMessage())+"\n\n");
		System.out.println(getJSON_Message(res.getMessage()));
		writer.flush();
	
	}
	private String getJSON_Message(Message m){
		ObjectMapper mapper = new ObjectMapper();
		String JSONString=null;
		try {
			JSONString= mapper.writeValueAsString(m);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return JSONString;
	}
}
