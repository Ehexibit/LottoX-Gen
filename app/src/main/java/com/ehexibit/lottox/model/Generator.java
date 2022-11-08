package com.ehexibit.lottox.model;

public class Generator {
    private Attributes attributes;
    private Lotto draw;
    private LottoResult result;
    public Generator(){

    }
    public Generator(Attributes attributes, Lotto draw){

        this.attributes = attributes;
        this.draw = draw;

    }
    public LottoResult result(){
        return  result;
    }
}
