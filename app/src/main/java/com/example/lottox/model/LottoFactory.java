package com.example.lottox.model;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
public class LottoFactory implements Serializable {
	
	/*This class will instantiate and initialise Objects to make It neat and organize
	LottoNumber
	LottoResult
	*/
	static int COUNT=0;
	public static final int ULTRA = 58;
	public static final int GRAND = 55;
	public static final int SUPER =49;
	public static final int MEGA = 45;
	public static final int LOTTO = 42;
	
	//Singleton
	private static LottoFactory oneLottoFactory=null;
	private Lotto drawState;

	
	private LottoNumber[] ultraNumber;
	private LottoNumber[] grandNumber;
	private LottoNumber[] superNumber;
	private LottoNumber[] megaNumber;
	private LottoNumber[] lottoNumber;
	
	private ArrayList<LottoResult> ultraResult;
	private ArrayList<LottoResult> grandResult;
	private ArrayList<LottoResult> superResult;
	private ArrayList<LottoResult> megaResult;
	private ArrayList<LottoResult> lottoResult;



	//private constructor so cannot be instantiated outside only at this class
	private LottoFactory(){
			
			//initialize our objects here
			//all state of LottoNumer and LottoResult because it get from here.
			
			//We will trace if our LottoFactory instance really just created 1 instance;
			COUNT++;
			
						
			
	}//End Constructor
	public static LottoFactory getInstance(){
		
	if(oneLottoFactory==null) oneLottoFactory = new LottoFactory();
	
	return oneLottoFactory;
	
	}//End getInstance
	
	public void init(){

		Log.d("Init Factory","Factory initialize");
//Create ArrayList of LottoNumber Object and LottoNumber Object added to it.	
     ultraNumber = new LottoNumber[ULTRA];
     grandNumber = new LottoNumber[GRAND];
     superNumber = new LottoNumber[SUPER];
     megaNumber = new LottoNumber[MEGA];	
     lottoNumber = new LottoNumber[LOTTO];		
    ultraResult = new ArrayList<>();
	grandResult = new ArrayList<>();
	superResult = new ArrayList<>();
	megaResult = new ArrayList<>();
	lottoResult = new ArrayList<>();
		
		//Create LottoNumber object then add to the ArrayList;
		LottoNumber a;
		
		for(int i=1; i<=ULTRA; i++){
			a = new LottoNumber();
			a.setID(i);
			a.setNumbers(ultraNumber);
			ultraNumber[i-1] = a;
		}
		
		for(int i=1; i<=GRAND; i++){
			a = new LottoNumber();
			a.setID(i);
			a.setNumbers(grandNumber);
		    grandNumber[i-1] = a;
		}
		
		for(int i=1; i<=SUPER; i++){
			a = new LottoNumber();
			a.setID(i);
			a.setNumbers(superNumber);
			superNumber[i-1] = a;
		}
		
		for(int i=1; i<=MEGA; i++){
			a = new LottoNumber();
			a.setID(i);
			a.setNumbers(megaNumber);
			megaNumber[i-1] = a;
		}
		
		for(int i=1; i<=LOTTO; i++){
			a = new LottoNumber();
			a.setID(i);
			a.setNumbers(lottoNumber);
			lottoNumber[i-1] = a;
		}
		loadResult();
	}//End void  init
	
	public void reset(){
		LottoNumber a;	
			
			for(int i=0; i<ULTRA; i++){
			a = ultraNumber[i];
			a.selected(false);
			a.setSuggested(false);
		}
		
		for(int i=0; i<GRAND; i++){
			a = grandNumber[i];
			a.selected(false);
			a.setSuggested(false);
		}
		
		for(int i=0; i<SUPER; i++){
			a = superNumber[i];
			a.selected(false);
			a.setSuggested(false);
		}
		
		for(int i=0; i<MEGA; i++){
			a = megaNumber[i];
			a.selected(false);
			a.setSuggested(false);
		}
		
		for(int i=0; i<LOTTO; i++){
			a = lottoNumber[i];
			a.selected(false);
			a.setSuggested(false);
		
		}
		//Load and Init Result Here Below paste the code
	
		
	} //End void reset 
	public LottoNumber[] getDraw(Lotto draw){
		LottoNumber[] d;
		switch(draw){
			case ULTRA:
			d = ultraNumber; break;
			case GRAND:
			d = grandNumber; break;
			case SUPER:
			d = superNumber; break;
			case MEGA:
			d = megaNumber; break;
			default :
			d = lottoNumber;
		}
		return d;       
		       
	}
	public LottoNumber[] getHotNumber(Lotto draw){
		LottoNumber[] d;
		switch(draw){
		case ULTRA:
		d = getHotNumber(ultraNumber); break;
		case GRAND:
		d = getHotNumber(grandNumber); break;
		case SUPER:
		d = getHotNumber(superNumber); break;
		case MEGA:
		d = getHotNumber(megaNumber); break;
		default :
		d = getHotNumber(lottoNumber);
	}
	return d;       
		
	}
	public LottoNumber[] getHotNumber(LottoNumber[] a){
		int[][] hotIDs = new int[a.length][2];
		LottoNumber[] d=new LottoNumber[a.length];
		for(int i=0; i<a.length; i++) {
			hotIDs[i][0] += i;
			hotIDs[i][1]  = a[i].getFrequency();
		}
		for(int i=0; i<a.length; i++) {
			for(int j=0; j<a.length; j++) {
				if(i==j) continue;
				if(a[i].getFrequency()>a[j].getFrequency()) {
					int ref = hotIDs[i][0];
					int val = hotIDs[i][1];
					hotIDs[i][0] = hotIDs[j][0];
					hotIDs[i][1] = hotIDs[j][1];
					hotIDs[j][0] = ref;
					hotIDs[j][1] = val;
				}
			}
		}
	for(int i=0; i<a.length; i++) {
		d[i] = a[hotIDs[i][0]];
	}
	return d;       
		
	}
	public ArrayList<LottoResult> getListResult(Lotto draw){
		ArrayList<LottoResult> res;
		switch(draw){
		case ULTRA:
		res = ultraResult; break;
		case GRAND:
		res = grandResult; break;
		case SUPER:
		res = superResult; break;
		case MEGA:
		res = megaResult; break;
		default :
		res = lottoResult;
		
		}
		return res;
	}
	public void setDraw(Lotto draw){
		this.drawState = draw;
	}
	public Lotto getDrawState(){
		return drawState;
	}
	private void loadResult(){
		Log.d("Load JSON: ","Loading!");
		ResourceManager.loadDataBase(ultraResult, Lotto.ULTRA);
		ResourceManager.loadDataBase(grandResult, Lotto.GRAND);
		ResourceManager.loadDataBase(superResult, Lotto.SUPER);
		ResourceManager.loadDataBase(megaResult, Lotto.MEGA);
		ResourceManager.loadDataBase(lottoResult, Lotto.LOTTO);
		Log.d("Load JSON: ", "Successful");
	}

	
}//End Class