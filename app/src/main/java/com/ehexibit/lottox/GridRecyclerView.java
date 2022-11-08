package com.ehexibit.lottox;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.GridLayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GridRecyclerView extends RecyclerView {
    public GridRecyclerView(@NonNull Context context) {
        super(context);
    }

    public GridRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public GridRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void attachLayoutAnimationParameters(View child, ViewGroup.LayoutParams params, int index, int count) {
        final LayoutManager layoutManager = getLayoutManager();
        if(getAdapter()!=null && layoutManager instanceof GridLayoutManager){
            GridLayoutAnimationController.AnimationParameters animationParameters =
                    (GridLayoutAnimationController.AnimationParameters) params.layoutAnimationParameters;
            if(animationParameters == null){
                animationParameters = new GridLayoutAnimationController.AnimationParameters();
                params.layoutAnimationParameters = animationParameters;
            }
            animationParameters.count = count;
            animationParameters.index = index;

            final int columns = ((GridLayoutManager)layoutManager).getSpanCount();
            animationParameters.columnsCount = columns;
            animationParameters.rowsCount = count/columns;

            final int invertIndex = count-1-index;
            animationParameters.column = columns-1-(invertIndex%columns);
            animationParameters.row = animationParameters.rowsCount-1-invertIndex/columns;
        }
        else

        super.attachLayoutAnimationParameters(child, params, index, count);
    }
}
