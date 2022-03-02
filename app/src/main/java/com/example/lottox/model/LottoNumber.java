package com.example.lottox.model;
public class LottoNumber{
	static int COUNT= 0; //count the LottoNumber object created
	static final int REF = 0;
	static final int VAL = 1;
	private Lotto draw;
	private int[][] IDs; //IDs of Best Pairs order 0 best n-1 least //Basically a key Map KeyPair<ID,Value> self implemented using 2D array. we will sort the Key order by index 0,0 Ref index 0,1 value index 1,0 ref index 1,1 value.
	private int ID; //If Number 1, ID is 1
	private String value;
	private boolean selected;
	private LottoNumber[] numbers;
	private int frequency=0;
	private boolean suggested=false;
	
    //Constructor	
	public LottoNumber() {
	  	init();
	  	COUNT++;
	}
	//Initial state
	public void init(){
	 	   selected = false;
	 	   value = "00";
	}
	public void setSuggested(boolean b){
		suggested = b;
	}
	public boolean getSuggested(){
		return suggested;
	}
	public void setDraw(Lotto draw){
		this.draw = draw;
	}
	public Lotto getDraw(){
		return draw;
	}
	//Set if already picked
	public void selected(boolean b){
		selected = b;
	}
	//Set ID
	public void setID(int ID){
		this.ID = ID;
		value = ""+(ID<10?"0"+ID:ID);
	}
	//Set Value string "01"
	public String getValue(){
		return value;
	}
	public boolean isSelected(){
		return selected;
	}
	public int getID(){
		sortBest();
		return ID;
	}
	public int[][] getIDs() {
		return IDs;
	}
	public void setNumbers(LottoNumber[] n){
		this.numbers = n;
	 	IDs = new int[n.length][2];
	 	for(int i=0; i<n.length; i++){
	 		IDs[i][REF] = i+1; //zero base index;
	 		IDs[i][VAL] = 0; //but value ref is 1-N
	 	}
	}
	public void setBestPairIDs(LottoNumber a){
		 IDs[a.getID()-1][VAL]++;
		 frequency++;
	}
	public int getFrequency() {
		return frequency;
	}
	public void sortBest() {
		
		for(int i=0; i<numbers.length; i++){
			for(int j=0; j<numbers.length; j++){
				if(i!=j) {
					if(IDs[i][VAL]>IDs[j][VAL]){
				
						//Temp Hold Data of Val and Ref
						int ref = IDs[i][REF]; 
						int val = IDs[i][VAL];
						//Swap Val and Ref 
						IDs[i][REF] = IDs[j][REF];
						IDs[i][VAL] = IDs[j][VAL];
						//Reassign Val and Ref   
						IDs[j][REF] = ref;
						IDs[j][VAL] = val;
			        
					}
				}
			}
		}
		
	}
	public LottoNumber getBestPair(){
		
	LottoNumber a = numbers[IDs[0][REF]-1];
	 //Temp assign bottom number;
		sortBest();	
		for(int i=0; i<numbers.length; i++){
			
			a = numbers[IDs[i][REF]-1];
			if(!a.isSelected()){

				break;
			}
			
		}
		return a;//Return a reference null if not found    
	}
	public LottoNumber getCommon(LottoNumber a){
		LottoNumber b = this.getBestPair();
		for(int i=0; i<numbers.length; i++){
 			if(b.getValue().equals(a.getBestPair().getValue())) break;
 			else if(!(numbers[IDs[i][REF]-1].isSelected())&&numbers[IDs[i][REF]-1]!=this) { //-1 because of IDs 1Base Index
 				b = numbers[IDs[i][REF]-1];
 			}	
 			
		}
		b.selected(true);
		return b;
	}
	
}

