package com.example.jinhui.statusbardemo;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.sofia.Sofia;
import com.example.sofia.StatusView;

/**
 * Email: 1004260403@qq.com
 * Created by jinhui on 2018/11/12.
 */
public class DrawerLayoutActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    private StatusView mStatusView;
    private Toolbar mToolbar;
    private View mHeaderView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mStatusView = findViewById(R.id.status_view);
        
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);  // 设置返回键
        }
        NestedScrollView nestedScrollView = findViewById(R.id.nested_scroll_view);
        mHeaderView = findViewById(R.id.header);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar,
                R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();  // 设置菜单按钮

        nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int headerHeight = mHeaderView.getHeight();
                int scrollDistance = Math.min(scrollY, headerHeight);
//                Log.e(TAG, "onScrollChange: " + scrollY + "--" + headerHeight); // scrollY值往下拉越来越大
//                int statusAlpha = (int) (scrollDistance / (headerHeight * 255F));  // 这样写有问题，下面是ok的
                int statusAlpha = (int) ((float) scrollDistance / (float) headerHeight * 255F);  // 正确写法
//                int statusAlpha = (int) (scrollDistance / (headerHeight / 255F));
//                Log.e(TAG, "onScrollChange: " + headerHeight * 255F);  // E/DrawerLayoutActivity: onScrollChange: 143565.0
                Log.e(TAG, "statusAlpha: " + statusAlpha); // statusAlpha为0
                setAnyBarAlpha(statusAlpha);
            }
        });

        // 这里不使用Sofia设置StatusBar颜色，用自己在布局中的，用Sofia设置会覆盖在DrawerLayout之上。
        Sofia.with(this)
                .navigationBarBackground(ContextCompat.getDrawable(this, R.color.colorNavigation))
                .invasionStatusBar()
                .statusBarBackground(Color.TRANSPARENT);

         // 设置透明度为0
        setAnyBarAlpha(0);

    }

    /**
     * 设置透明度
     *
     * @param alpha
     */
    private void setAnyBarAlpha(int alpha) {
        mStatusView.getBackground().mutate().setAlpha(alpha);
        mToolbar.getBackground().mutate().setAlpha(alpha);
    }
}
