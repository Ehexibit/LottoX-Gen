package com.example.lottox.model;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ResourceManager {
	public static void load(Context context, ArrayList<LottoResult> lotto, Lotto type){

	   String resGrand = "input/Lotto55.txt";
	   String resSuper = "input/Lotto49.txt";
	   String resMega = "input/Lotto45.txt";
	   String resLotto = "input/Lotto42.txt";
	   String resUltra = "input/Lotto58.txt";
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
   private static void load(Context context, ArrayList<LottoResult> lotto, String s, Lotto type){
  LottoFactory fc = LottoFactory.getInstance();
	   LottoNumber[] lottoNumbers = new LottoNumber[6];
	   LottoNumber[] n = fc.getDraw(type);
   //	File file = new File(s);
       try{
		   InputStream file = context.getAssets().open(s);
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