package com.ehexibit.lottox;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ehexibit.lottox.model.Lotto;
import com.ehexibit.lottox.model.LottoFactory;
import com.ehexibit.lottox.model.LottoNumber;

import java.util.List;

public class BallsViewAdapter extends RecyclerView.Adapter<BallsViewAdapter.BallsViewHolder>{
    List<BallNumber> ballNumbers;
    RecyclerView recyclerView;
    public BallsViewAdapter(List<BallNumber> ballNumbers, RecyclerView recyclerView){
        this.ballNumbers = ballNumbers;
        this.recyclerView = recyclerView;
    }
    public static class BallsViewHolder extends RecyclerView.ViewHolder{
        TextView ballsView;
        public BallsViewHolder(@NonNull View itemView) {
            super(itemView);
            ballsView  = itemView.findViewById(R.id.balls);
        }
    }
    @NonNull
    @Override
    public BallsViewAdapter.BallsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.number_layout,parent,false);
        return new BallsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BallsViewAdapter.BallsViewHolder holder, int position) {
        if(ballNumbers.get(position).number.getSuggested())
            holder.ballsView.setBackgroundResource(R.drawable.bgset);
       else holder.ballsView.setBackgroundResource(setBackground(ballNumbers.get(position).number.getDraw()));

        holder.ballsView.setText(ballNumbers.get(position).number.getValue());
        holder.ballsView.setOnClickListener(view -> {

            holder.ballsView.setText(ballNumbers.get(position).number.getValue());

           holder.ballsView.setBackgroundResource(R.drawable.bgset);

            LottoFactory factory = LottoFactory.getInstance();
            factory.reset();

                LottoNumber[] numbers = new LottoNumber[6];
                numbers[0] = ballNumbers.get(position).number;  numbers[0].selected(true);
                numbers[1] = numbers[0].getBestPair();          numbers[1].selected(true);
                numbers[2] = numbers[0].getCommon(numbers[1]);  numbers[2].selected(true);
                numbers[3] = numbers[2].getBestPair();          numbers[3].selected(true);
                numbers[4] = numbers[2].getCommon(numbers[3]);  numbers[4].selected(true);
                numbers[5] = numbers[4].getBestPair();          numbers[5].selected(true);

                for(LottoNumber n:numbers) {
                    n.setSuggested(true);
                    n.selected(false);
                }
               // recyclerView.setAdapter(this);
                notifyDataSetChanged();






        });



    }
    private int setBackground(Lotto draw){

        switch (draw){

            case ULTRA: return R.drawable.bg58;
            case GRAND: return R.drawable.bg55;
            case SUPER: return R.drawable.bg49;
            case MEGA: return R.drawable.bg45;
            default:return R.drawable.bg42;
        }

    }

    @Override
    public int getItemCount() {
        return ballNumbers.size();
    }
}

