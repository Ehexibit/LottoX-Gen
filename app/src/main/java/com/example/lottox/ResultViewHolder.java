package com.example.lottox;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ResultViewHolder extends RecyclerView.ViewHolder {

    TextView[] numbers = new TextView[6];
    public ResultViewHolder(View itemView) {

        super(itemView);

        numbers[0] = itemView.findViewById(R.id.sixBalls1);
        numbers[1] = itemView.findViewById(R.id.sixBalls2);
        numbers[2] = itemView.findViewById(R.id.sixBalls3);
        numbers[3] = itemView.findViewById(R.id.sixBalls4);
        numbers[4] = itemView.findViewById(R.id.sixBalls5);
        numbers[5] = itemView.findViewById(R.id.sixBalls6);


    }
}
