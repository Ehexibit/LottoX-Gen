package com.ehexibit.lottox;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ehexibit.lottox.model.Lotto;
import com.ehexibit.lottox.model.LottoFactory;
import com.ehexibit.lottox.model.LottoNumber;
import com.ehexibit.lottox.model.LottoResult;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GeneratorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GeneratorFragment extends Fragment {

    private static final TextView[] lastResult = new TextView[6];

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LottoFactory factory;

    public GeneratorFragment() {
        super(R.layout.generator_layout);
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Generator.
     */
    // TODO: Rename and change types and number of parameters
    public static GeneratorFragment newInstance(String param1, String param2) {
        GeneratorFragment fragment = new GeneratorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        Log.d("GeneratorFragment","New Instance");
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        factory = LottoFactory.getInstance();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        Log.d("GeneratorFragment","OnCreate Called");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("GeneratorFragment","OnCreateView Called");
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.generator_layout,container,false);
        GridRecyclerView recyclerView = view.findViewById(R.id.ballsRecycler);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),8);
        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(getContext(),R.anim.grid_animation);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutAnimation(animationController);

        GridRecyclerView resultRecyclerview = view.findViewById(R.id.resultRecyclerView);




        initDrawBalls(recyclerView,view);

        switch (factory.getDrawState()){
            case ULTRA: view.findViewById(R.id.boardConstraint).setBackgroundResource(R.drawable.constraint_bg58);break;
            case GRAND: view.findViewById(R.id.boardConstraint).setBackgroundResource(R.drawable.constraint_bg55);break;
            case SUPER: view.findViewById(R.id.boardConstraint).setBackgroundResource(R.drawable.constraint_bg49);break;
            case MEGA: view.findViewById(R.id.boardConstraint).setBackgroundResource(R.drawable.constraint_bg45);break;
            default: view.findViewById(R.id.boardConstraint).setBackgroundResource(R.drawable.constraint_bg42);break;
        }

        initHeaderResult(view);
        generate(resultRecyclerview,view);


        return view;
    }

    private void generate(GridRecyclerView recyclerView,View view){
       Button generateButton = view.findViewById(R.id.generateButton);

       generateButton.setOnClickListener(view1 -> {
            try{

                ArrayList<LottoResult> lottoResult = factory.getListResult(factory.getDrawState());
                LottoNumber[] drawNumbers = factory.getDraw(factory.getDrawState());
               // Toast.makeText(view.getContext(), "DrawState "+factory.getDrawState(), Toast.LENGTH_SHORT).show();
                int[] resultID = lottoResult.get(0).getIDs();
                List<Generated> generatedRes = new ArrayList<>();
                for (int i=0; i<resultID.length; i++) {

                    factory.reset();
                    LottoNumber[] numbers = new LottoNumber[6];
                    numbers[0] = drawNumbers[resultID[i] - 1];      numbers[0].selected(true);
                    numbers[1] = numbers[0].getBestPair();          numbers[1].selected(true);
                    numbers[2] = numbers[0].getCommon(numbers[1]);  numbers[2].selected(true);
                    numbers[3] = numbers[2].getBestPair();          numbers[3].selected(true);
                    numbers[4] = numbers[2].getCommon(numbers[3]);  numbers[4].selected(true);
                    numbers[5] = numbers[4].getBestPair();          numbers[5].selected(true);

                    generatedRes.add(new Generated(factory.getDrawState(), numbers));
                }
                ResultViewAdapter resultViewAdapter = new ResultViewAdapter(generatedRes);
                recyclerView.setAdapter(resultViewAdapter);
                RecyclerView.LayoutManager resultLayoutManager = new GridLayoutManager(getContext(),1);
                LayoutAnimationController animationResultController = AnimationUtils.loadLayoutAnimation(getContext(),R.anim.grid_animation);
                recyclerView.setLayoutManager(resultLayoutManager);
                recyclerView.setLayoutAnimation(animationResultController);




            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }


           //lastResult[0].setText(""+str[0]);
           //lastResult[1].setText(""+str[1]);
           //lastResult[2].setText(""+str[2]);
           //lastResult[3].setText(""+str[3]);
           //lastResult[4].setText(""+str[4]);
           //lastResult[5].setText(""+str[5]);
           //Toast.makeText(view1.getContext(), "String content : "+ Arrays.toString(str), Toast.LENGTH_SHORT).show();


       });
    }
    private int setBallBGTheme(Lotto draw){

        switch (draw){
            case ULTRA: return R.drawable.bg58;
            case GRAND: return R.drawable.bg55;
            case SUPER: return R.drawable.bg49;
            case MEGA: return   R.drawable.bg45;
            default: return R.drawable.bg42;

        }

    }
    private void initHeaderResult(View view){
        lastResult[0] = view.findViewById(R.id.boardBalls1);
        lastResult[1] = view.findViewById(R.id.boardBalls2);
        lastResult[2] = view.findViewById(R.id.boardBalls3);
        lastResult[3] = view.findViewById(R.id.boardBalls4);
        lastResult[4] = view.findViewById(R.id.boardBalls5);
        lastResult[5] = view.findViewById(R.id.boardBalls6);
        for (TextView textView:lastResult) textView.setBackgroundResource(setBallBGTheme(factory.getDrawState()));
        ImageView head = view.findViewById(R.id.imageDraw);
        switch (factory.getDrawState()){
            case ULTRA: head.setImageResource(R.drawable.ultra_lotto); break;
            case GRAND: head.setImageResource(R.drawable.grand_lotto); break;
            case SUPER: head.setImageResource(R.drawable.super_lotto); break;
            case MEGA: head.setImageResource(R.drawable.mega_lotto); break;
            default: head.setImageResource(R.drawable.lotto); break;
        }
        try {
            LottoResult result = factory.getListResult(factory.getDrawState()).get(0);
            for(int i=0; i<lastResult.length; i++){
                lastResult[i].setText(""+result.getIDs()[i]);
            }
        } catch (Exception e){
            for(int i=0; i<lastResult.length; i++){
                lastResult[i].setText("0"+(i+1));
            }
        }
    }
    private void initDrawBalls(RecyclerView recyclerView,View view){

        LottoNumber[] numbers;

        numbers = factory.getHotNumber(factory.getDrawState());

        List<BallNumber> listResult = new ArrayList<>();
        for (LottoNumber number : numbers) {
            number.setDraw(factory.getDrawState());
            listResult.add(new BallNumber(number));
        }
        BallsViewAdapter ballsViewAdapter = new BallsViewAdapter(listResult,recyclerView);
        recyclerView.setAdapter(ballsViewAdapter);


        ImageView imageView = view.findViewById(R.id.imageDraw);
        imageView.setOnClickListener(view1 -> {

        });


    }
}