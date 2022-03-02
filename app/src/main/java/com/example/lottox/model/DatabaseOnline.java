package com.example.lottox.model;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.json.JSONArray;
	import org.json.JSONException;
	import org.json.JSONObject;

	public class DatabaseOnline{

	  private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		  try (InputStream is = new URL(url).openStream()) {
			  BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
			  String jsonText = readAll(rd);
			  return new JSONObject(jsonText);
		  }
	  }

	  public static ArrayList<String> loadDataBase(Lotto draw) throws IOException, JSONException {
		 
		ArrayList<String> results = new ArrayList<>();
		String dataUrl = "https://sheets.googleapis.com/v4/spreadsheets/1-36SeGUlQ5ulhV-DKOZgj2zFp3QIDH155EcdOKgayEM/values/Sheet2!A1:A150?majorDimension=ROWS&key=AIzaSyCF--0ILSqLvmflDy5DkUFi-6VUDlJVc7E";
		switch(draw) {
		case ULTRA:
			dataUrl = "https://sheets.googleapis.com/v4/spreadsheets/1-36SeGUlQ5ulhV-DKOZgj2zFp3QIDH155EcdOKgayEM/values/Sheet5!A1:A150?majorDimension=ROWS&key=AIzaSyCF--0ILSqLvmflDy5DkUFi-6VUDlJVc7E";
			break;
		case GRAND:
			dataUrl = "https://sheets.googleapis.com/v4/spreadsheets/1-36SeGUlQ5ulhV-DKOZgj2zFp3QIDH155EcdOKgayEM/values/Sheet4!A1:A150?majorDimension=ROWS&key=AIzaSyCF--0ILSqLvmflDy5DkUFi-6VUDlJVc7E";
			break;
		case SUPER:
			dataUrl = "https://sheets.googleapis.com/v4/spreadsheets/1-36SeGUlQ5ulhV-DKOZgj2zFp3QIDH155EcdOKgayEM/values/Sheet3!A1:A150?majorDimension=ROWS&key=AIzaSyCF--0ILSqLvmflDy5DkUFi-6VUDlJVc7E";
			break;
		case MEGA:
			dataUrl = "https://sheets.googleapis.com/v4/spreadsheets/1-36SeGUlQ5ulhV-DKOZgj2zFp3QIDH155EcdOKgayEM/values/Sheet2!A1:A150?majorDimension=ROWS&key=AIzaSyCF--0ILSqLvmflDy5DkUFi-6VUDlJVc7E";
			break;
		case LOTTO:
			dataUrl = "https://sheets.googleapis.com/v4/spreadsheets/1-36SeGUlQ5ulhV-DKOZgj2zFp3QIDH155EcdOKgayEM/values/Sheet1!A1:A150?majorDimension=ROWS&key=AIzaSyCF--0ILSqLvmflDy5DkUFi-6VUDlJVc7E";
		default:			
			break;
			
		}
	    JSONObject json = readJsonFromUrl(dataUrl);
	       
	    JSONArray jry = json.getJSONArray("values");
	    
	    int size = jry.length();
	    
	    String[][] strn = new String[size][];
	    
	    
	    	for(int i=0; i<jry.length(); i++) {
	    		JSONArray jry2 = (JSONArray) jry.get(i);
	    		String[] ary = new String[jry2.length()];
	    		for(int j=0; j<jry2.length(); j++) {
	    			ary[j] = (String) jry2.get(j);
	    		}
	    		strn[i] = ary;
	    	}
	    //System.out.println(strn[0][0]);

	    for(int i=0; i<size; i++) {
	    	results.add(strn[i][0]);
	    }
	    
	    return results;
	  }
	}



