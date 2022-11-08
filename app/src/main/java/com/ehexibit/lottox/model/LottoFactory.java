package com.ehexibit.lottox.model;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.Serializable;
import java.time.LocalDate;
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
	
	public void init(Context context){

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
		Log.d("Load File","File Loading!");
		loadResult(context);
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
	private void loadResult(Context context){

		//SharedPref AppData
		//InitialState = true; else false;
		//lastUpdate = "Date";
		//
		LocalDate currentDate = LocalDate.now(); //Get Current Date
		LocalDate lastUpdate = LocalDate.now(); //Default
		SharedPreferences sharedPreferences = context.getSharedPreferences("AppUpdate",Context.MODE_PRIVATE);
		boolean initialState = sharedPreferences.getBoolean("InitialState",true);
		String sts = ""+currentDate.toString()+initialState+lastUpdate.toString();
		Log.d("Status",sts);
		if(initialState){
			//updateOnline and set last update

			update(context);
			sharedPreferences.edit().putBoolean("InitialState", false).apply(); //save state
		}
		else {
			lastUpdate = LocalDate.parse(sharedPreferences.getString("LastUpdate", LocalDate.now().toString())); //get state


			if (lastUpdate.compareTo(currentDate) == 0) {
				//load local data base


				ResourceManager.load(context, ultraResult, Lotto.ULTRA);
				ResourceManager.load(context, grandResult, Lotto.GRAND);
				ResourceManager.load(context, superResult, Lotto.SUPER);
				ResourceManager.load(context, megaResult, Lotto.MEGA);
				ResourceManager.load(context, lottoResult, Lotto.LOTTO);




			} else if (isNetWorkAvailable(context)) {
				//load online data base


				update(context);
				ResourceManager.load(context, ultraResult, Lotto.ULTRA);
				ResourceManager.load(context, grandResult, Lotto.GRAND);
				ResourceManager.load(context, superResult, Lotto.SUPER);
				ResourceManager.load(context, megaResult, Lotto.MEGA);
				ResourceManager.load(context, lottoResult, Lotto.LOTTO);

				lastUpdate = LocalDate.now();
				sharedPreferences.edit().putString("LastUpdate", lastUpdate.toString()).apply();
			} else {

				ResourceManager.load(context, ultraResult, Lotto.ULTRA);
				ResourceManager.load(context, grandResult, Lotto.GRAND);
				ResourceManager.load(context, superResult, Lotto.SUPER);
				ResourceManager.load(context, megaResult, Lotto.MEGA);
				ResourceManager.load(context, lottoResult, Lotto.LOTTO);

			}
		}



	}
	private static void update(Context context){
		ResourceManager.updateFile(context,Lotto.ULTRA);
		ResourceManager.updateFile(context,Lotto.GRAND);
		ResourceManager.updateFile(context,Lotto.SUPER);
		ResourceManager.updateFile(context,Lotto.MEGA);
		ResourceManager.updateFile(context,Lotto.LOTTO);

	}
	private static boolean isNetWorkAvailable(Context context){
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		return networkInfo != null && networkInfo.isConnected();
	}

	
}//End Class