package com.example.lottox.model;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ResourceManager {
	public static void load(Context context, ArrayList<LottoResult> lotto, Lotto type){

	   String resGrand = "Lotto55.txt";
	   String resSuper = "Lotto49.txt";
	   String resMega = "Lotto45.txt";
	   String resLotto = "Lotto42.txt";
	   String resUltra = "Lotto58.txt";
	   switch(type){
   		 case ULTRA:
			 load(context,lotto, resUltra, type);
			 Log.d("Load Factory","Factory Loaded");break;
   		 case GRAND:
			 load(context,lotto, resGrand, type);
			 Log.d("Load Factory","Factory Loaded");break;
   		 case SUPER:
			 load(context,lotto, resSuper, type);
			 Log.d("Load Factory","Factory Loaded");break;
   		 case MEGA:
			 load(context,lotto, resMega, type);
			 Log.d("Load Factory","Factory Loaded");break;
   		 case LOTTO:
			 load(context,lotto, resLotto, type);
			 Log.d("Load Factory","Factory Loaded");break;
   		
   	}
	}
   private static void load(Context context, ArrayList<LottoResult> lotto, String path, Lotto type){
  LottoFactory fc = LottoFactory.getInstance();
	   LottoNumber[] lottoNumbers = new LottoNumber[6];
	   LottoNumber[] n = fc.getDraw(type);
   //	File file = new File(s);
       try{
		   InputStream file = context.openFileInput(path);
		   Log.d("Init File","File Loading");
		   Scanner sc = new Scanner(file);
        	while(sc.hasNextLine()){
        		String[] st = sc.nextLine().split("-");
        		
        		for(int i=0; i<st.length; i++){
        			int index = Integer.parseInt(st[i]);
        			lottoNumbers[i] = n[index-1];
        		}

				LottoResult rs = new LottoResult(lottoNumbers);
        		lotto.add(rs);
        		
        	}


       } catch (IOException e) {
		   e.printStackTrace();
		   Log.d("Load Factory","Factory Loading Failed");
	   }

   }
   public static void update(Context context,ArrayList<String> result,Lotto type){
	   switch(type) {
		   case ULTRA:

			   Log.d("Load Factory", "Factory Loaded");
			   break;
		   case GRAND:

			   Log.d("Load Factory", "Factory Loaded");
			   break;
		   case SUPER:

			   Log.d("Load Factory", "Factory Loaded");
			   break;
		   case MEGA:

			   Log.d("Load Factory", "Factory Loaded");
			   break;
		   case LOTTO:

			   Log.d("Load Factory", "Factory Loaded");
			   break;
	   }

   }
   public static void updateFile(Context context,Lotto type){
		String path = "";
		switch (type){
			case ULTRA:	path = path.concat("Lotto58.txt"); break;
			case GRAND:	path = path.concat("Lotto55.txt"); break;
			case SUPER:	path = path.concat("Lotto49.txt"); break;
			case MEGA:	path = path.concat("Lotto45.txt"); break;
			case LOTTO: path = path.concat("Lotto42.txt"); break;

		}
		try{
			FileOutputStream fOut = context.openFileOutput(path,Context.MODE_PRIVATE);
			OutputStreamWriter owrite = new OutputStreamWriter(fOut);
			ArrayList<String> result = DatabaseOnline.loadDataBase(type);
			for(String r:result)
			owrite.write(r+"\n");
			owrite.flush();
			owrite.close();

		}catch (IOException | JSONException e){
			e.printStackTrace();
		}

   }
   /*
   public static ArrayList<String> loadFile(Context context, Lotto type){
		ArrayList<String> result = new ArrayList<>();
		//read file while(sc.hasNextLine)result.add(st);
		return result;
   }
   */
	public static void loadDataBase(ArrayList<LottoResult> lotto, Lotto type){

		LottoFactory fc = LottoFactory.getInstance();
		LottoNumber[] lottoNumbers = new LottoNumber[6];
		LottoNumber[] n = fc.getDraw(type);

		try{

			ArrayList<String> sourceData;

			sourceData = DatabaseOnline.loadDataBase(type);
			int size = sourceData.size();
			for(int i=0; i<size; i++){

				String[] st = sourceData.get(i).split("-");
					System.out.println(sourceData.get(i));
				for(int j=0; j<st.length; j++){
					int index = Integer.parseInt(st[j]);
					//		System.out.print("Index = "+index);
					//		System.out.println(" i = "+i);
					lottoNumbers[j] = n[index-1];
				}

				LottoResult rs = new LottoResult(lottoNumbers);
				lotto.add(rs);
			}


		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  
	
	
	
}