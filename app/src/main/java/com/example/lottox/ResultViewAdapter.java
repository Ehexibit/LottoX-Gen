package com.example.lottox;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResultViewAdapter extends RecyclerView.Adapter<ResultViewHolder> {
    List<Generated> generatedBalls;
public ResultViewAdapter(List<Generated> generatedBalls){
    this.generatedBalls = generatedBalls;

}

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.six_balls,parent,false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {



        switch(generatedBalls.get(position).draw){
            case ULTRA: for(TextView textView: holder.numbers) textView.setBackgroundResource(R.drawable.bg58);break;
            case GRAND: for(TextView textView: holder.numbers) textView.setBackgroundResource(R.drawable.bg55);break;
            case SUPER: for(TextView textView: holder.numbers) textView.setBackgroundResource(R.drawable.bg49);break;
            case MEGA: for(TextView textView: holder.numbers) textView.setBackgroundResource(R.drawable.bg45);break;
            default:  for(TextView textView: holder.numbers) textView.setBackgroundResource(R.drawable.bg42);break;

        }//End Switch

        /* Print Result of GeneratedBallRecyclerView */
        for(int i=0; i<holder.numbers.length; i++) {

            String txt = ""+generatedBalls.get(position).numbers[i].getID();
            holder.numbers[i].setText(txt);
        }

    }

    @Override
    public int getItemCount() {
        return generatedBalls.size();
    }
}
