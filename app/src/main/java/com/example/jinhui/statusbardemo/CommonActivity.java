package com.example.jinhui.statusbardemo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.yanzhenjie.sofia.Sofia;


/**
 * Email: 1004260403@qq.com
 * Created by jinhui on 2018/11/12.
 */
public class CommonActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    private Toolbar mToolbar;
    private View mHeaderView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        NestedScrollView nestedScrollView = findViewById(R.id.nested_scroll_view);
        mHeaderView = findViewById(R.id.header);

        final int startColor = ContextCompat.getColor(this, R.color.colorPrimary);
        final int endColor = ContextCompat.getColor(this, R.color.colorNavigation);

        nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int headerHeight = mHeaderView.getHeight();
                int scrollDistance = Math.min(scrollY, headerHeight);
//                float fraction = scrollDistance / headerHeight;
//                Log.e(TAG, "onScrollChange: " + fraction);  // 数值变化为0、1
                float fraction = (float) scrollDistance / (float) headerHeight;  // 需要置为float类型，才能有数值变化哦
                Log.e(TAG, "onScrollChange: " + fraction);  // 数值变化为0~1的范围
                setToolbarStatusBarAlpha(evaluate(fraction, startColor, endColor));
                setNavigationViewColor(evaluate(fraction, startColor, endColor));
            }
        });

        Sofia.with(this)
                .statusBarBackground(ContextCompat.getColor(this, R.color.colorPrimary))
                .navigationBarBackground(ContextCompat.getDrawable(this, R.color.colorNavigation));


    }

    private void setNavigationViewColor(int color) {
        Sofia.with(this).navigationBarBackground(color);
    }

    private void setToolbarStatusBarAlpha(int color) {
        DrawableCompat.setTint(mToolbar.getBackground().mutate(), color);
        Sofia.with(this).statusBarBackground(color);
    }

    public int evaluate(float fraction, int startValue, int endValue) {
        int startA = (startValue >> 24) & 0xff;
        int startR = (startValue >> 16) & 0xff;
        int startG = (startValue >> 8) & 0xff;
        int startB = startValue & 0xff;

        int endA = (endValue >> 24) & 0xff;
        int endR = (endValue >> 16) & 0xff;
        int endG = (endValue >> 8) & 0xff;
        int endB = endValue & 0xff;

        return ((startA + (int) (fraction * (endA - startA))) << 24) |
                ((startR + (int) (fraction * (endR - startR))) << 16) |
                ((startG + (int) (fraction * (endG - startG))) << 8) |
                ((startB + (int) (fraction * (endB - startB))));
    }

}
