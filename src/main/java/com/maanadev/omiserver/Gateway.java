package com.maanadev.omiserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maanadev.messages.Message;
import com.maanadev.messages.PlayerCardChangeReq;
import com.maanadev.messages.Response;
import com.maanadev.messages.TES;

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
		
		sendSSE(resp.getWriter(), 	game.handleSSERequest(req));
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
	        String json = "";
	        if(br != null){
	            json = br.readLine();
	        }
	        br.close();
	        
	        // 2. initiate jackson mapper
	        ObjectMapper mapper = new ObjectMapper();
	 
	        // 3. Convert received JSON to Article
	        PlayerCardChangeReq cardChangeReq = mapper.readValue(json,PlayerCardChangeReq.class);
	        
	        
	       
	        System.out.println(cardChangeReq.getCard().split("/")[1].split("\\.")[0]);
	        game.handlePOSTRequst(cardChangeReq);
	        // 4. Set response type to JSON
	        resp.setContentType("application/json");            
	        PrintWriter w= resp.getWriter();
	        w.write(cardChangeReq.getUserId());
	        w.close();
	   
	}
	private void sendSSE(PrintWriter writer,Response res){
	//	System.out.println(res.getUserId());
		TES tes= new TES();
		tes.setId(res.getUserId());
		tes.setM(res.getMessage());
		writer.print("id:"+res.getUserId()+"\n");
		//writer.print("data:"+"{ id:"+res.getUserId()+",m:"+getJSON_Message(res.getMessage())+"\n\n");
		writer.println("data:"+getJSON_Message(tes)+"\n\n");
		//System.out.println(getJSON_Message(res.getMessage()));
		writer.flush();
	
	}
	private String getJSON_Message(TES m){
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
