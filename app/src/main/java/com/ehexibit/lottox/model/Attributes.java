package com.ehexibit.lottox.model;

public class Attributes {
    //We will use this class to validate the option selected by the user
    //It will store data temporary the Generator class will use this class as well as
    int sum = 6;
    int even;
    int odd;
    int hot;
    int cold;
    public Attributes(int even,int odd,int hot, int cold){
        this.even = even;
        this.odd = odd;
        this.hot = hot;
        this.cold = cold;
    }
    public boolean isValidHotCold(){
        return sum - hot == cold || sum - cold == hot;
    }
    public boolean isValidEvenOdd(){
        return sum - even == odd || sum - odd == even;
    }

}
