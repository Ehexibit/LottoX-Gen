package com.example.lottox;

import com.example.lottox.model.Lotto;

public class MainMenu {
    String[] numbers = {"01","02","03","04","05","06"};
    String title;
    Lotto draw;

    int logoID;
    MainMenu(String title, int logoID,Lotto draw){
        this.title=title;
        this.logoID=logoID;
        this.draw = draw;
    }
}
