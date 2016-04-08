package com.maanadev.cards;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maanadev.messages.ImageBind;
import com.maanadev.messages.Message;

public class A {
	
public static void main(String[] args) {
	
	Message m = new Message();
	m.setCard1("cards/34.png");
	m.setCard2(null);
	m.setCard3("cards/22.png");
	m.setMycard("cards/22.png");
	m.setMessage("adsasd");
	m.setShowCards(true);
	m.setShowHand(false);
	
	
	ImageBind im= new ImageBind();
	im.setImage("cards/22.png");
	ImageBind im1= new ImageBind();
	im1.setImage("cards/23.png");
	ArrayList<ImageBind> list = new ArrayList<ImageBind>();
	list.add(im1);
	list.add(im);
	
	m.setCards(list);
	
	
	System.out.println(getMessageString(m));
}


private  static String getMessageString(Message m){
	ObjectMapper mapper = new ObjectMapper();
	String jsonInString=null;
	try {
		jsonInString= mapper.writeValueAsString(m);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	return jsonInString;
}

}