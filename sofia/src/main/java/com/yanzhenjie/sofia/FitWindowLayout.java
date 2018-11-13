package com.yanzhenjie.sofia;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Email: 1004260403@qq.com
 * Created by jinhui on 2018/11/13.
 */
public class FitWindowLayout extends ViewGroup {

    public FitWindowLayout(Context context) {
        super(context);
    }

//    public FitWindowLayout(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public FitWindowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int allHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            int viewHeight = view.getMeasuredHeight();
            view.layout(l, allHeight, r, allHeight + viewHeight);
            allHeight += viewHeight;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int contentViewHeight = 0;
        int childCount = getChildCount();
        int menuWidthSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getMode(widthMeasureSpec));
        int menuHeightSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.getMode(heightMeasureSpec));
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            view.measure(menuWidthSpec, menuHeightSpec);
            contentViewHeight += view.getMeasuredHeight();
        }
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), contentViewHeight);
    }
}
