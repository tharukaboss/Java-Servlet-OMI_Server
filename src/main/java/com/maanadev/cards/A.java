package com.maanadev.cards;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maanadev.messages.ImageBind;
import com.maanadev.messages.Message;

public class A {
	
public static void main(String[] args) {
	
	DualHashBidiMap map = new DualHashBidiMap();
	map.put("adsasd", 1);
	
	
	System.out.println(map.get("adsasd"));
	System.out.println(map.getKey(1));
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