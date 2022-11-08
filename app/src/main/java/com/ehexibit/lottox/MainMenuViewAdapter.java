package com.ehexibit.lottox;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.ehexibit.lottox.model.LottoFactory;
import com.ehexibit.lottox.model.LottoNumber;

import java.util.List;

public class MainMenuViewAdapter extends RecyclerView.Adapter<MainMenuViewAdapter.MenuViewHolder> {
    List<MainMenu> menuList;
    LottoFactory factory;
    GeneratorFragment generatorFragment;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    public MainMenuViewAdapter(List<MainMenu> menus){
        generatorFragment = new GeneratorFragment();
        this.menuList = menus;

    }
    public void setFragmentManager(FragmentManager fragmentManager){

        this.fragmentManager = fragmentManager;


    }
    public static class MenuViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView titleHeader;
        TextView[] numbers = new TextView[6];


     public MenuViewHolder(View itemView) {

            super(itemView);
            image = itemView.findViewById(R.id.imageView);
            titleHeader = itemView.findViewById(R.id.titleHeader);
            numbers[0] = itemView.findViewById(R.id.luckyNumber1);
            numbers[1]= itemView.findViewById(R.id.luckyNumber2);
            numbers[2] = itemView.findViewById(R.id.luckyNumber3);
            numbers[3]= itemView.findViewById(R.id.luckyNumber4);
            numbers[4] = itemView.findViewById(R.id.luckyNumber5);
            numbers[5] = itemView.findViewById(R.id.luckyNumber6);

        }

    }



    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_layout,parent,false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {

        factory = LottoFactory.getInstance();
        holder.titleHeader.setText(menuList.get(position).title);
        holder.image.setImageResource(menuList.get(position).logoID);


        for(int i=0; i<holder.numbers.length; i++) {
            holder.numbers[i].setText(menuList.get(position).numbers[i]);
        }
        for(TextView textView: holder.numbers)
            textView.setBackgroundResource(R.drawable.bg42);



       // ConstraintLayout constraintLayout = holder.itemView.findViewById(R.id.customUILayout);


        switch(menuList.get(position).draw){
            case ULTRA:

                for(TextView textView: holder.numbers)
                    textView.setBackgroundResource(R.drawable.bg58);

                break;
            case GRAND:
                for(TextView textView: holder.numbers)
                    textView.setBackgroundResource(R.drawable.bg55);
                break;
            case SUPER:
                for(TextView textView: holder.numbers)
                    textView.setBackgroundResource(R.drawable.bg49);
                break;
            case MEGA:
                for(TextView textView: holder.numbers)
                    textView.setBackgroundResource(R.drawable.bg45);
                break;

        }
        holder.image.setOnClickListener(view -> {
            //Toast.makeText(view.getContext(),"Title Click!",Toast.LENGTH_SHORT).show();

            transaction = fragmentManager.beginTransaction();

            if(!generatorFragment.isAdded()) {

                transaction.replace(R.id.mainContainer,generatorFragment);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.addToBackStack(null);
                transaction.commit();
                factory.setDraw(menuList.get(position).draw);

            }
        });

        holder.itemView.setOnClickListener(view -> {
           // Toast.makeText(view.getContext(), menuList.get(position).title,Toast.LENGTH_SHORT).show();
            int[] rNumbers = new int[6];

            int randomInt;
            Animation rotation = AnimationUtils.loadAnimation(view.getContext(), R.anim.rotation);
            Animation[] zooms = new Animation[6];

            for(int i=0;i<zooms.length;i++)zooms[i] = AnimationUtils.loadAnimation(view.getContext(),R.anim.zoon_in);

            LottoNumber a,b,c,d,e,f;

            int limit = factory.getDraw(menuList.get(position).draw).length;
            randomInt = (int) (Math.random()*limit);

            factory.reset();

            a = factory.getDraw(menuList.get(position).draw)[randomInt]; a.selected(true);
            b = a.getBestPair(); b.selected(true);
            c = b.getCommon(a); c.selected(true);
            d = c.getBestPair(); d.selected(true);
            e = d.getCommon(c); e.selected(true);
            f = e.getBestPair();

            //Use Array Int to easily set TextView.setText using loop

            rNumbers[0] = a.getID();
            rNumbers[1] = b.getID();
            rNumbers[2] = c.getID();
            rNumbers[3] = d.getID();
            rNumbers[4] = e.getID();
            rNumbers[5] = f.getID();

            rotation.setDuration(600);

            holder.image.setAnimation(rotation);

            zooms[1].setStartOffset(100);
            zooms[2].setStartOffset(150);
            zooms[3].setStartOffset(200);
            zooms[4].setStartOffset(300);
            zooms[5].setStartOffset(350);


            for(int i=0; i<holder.numbers.length; i++){

                holder.numbers[i].startAnimation(zooms[i]);
            }
            for(int i=0; i<holder.numbers.length; i++){

                holder.numbers[i].setText((rNumbers[i]<10)?"0"+rNumbers[i]: ""+rNumbers[i]);
            }

        });



    }
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView view){
        super.onAttachedToRecyclerView(view);
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }
}
