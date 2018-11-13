package com.yanzhenjie.sofia;

import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Email: 1004260403@qq.com
 * Created by jinhui on 2018/11/13.
 */
public interface Bar {

    Bar statusBarDarkFont();

    Bar statusBarLightFont();

    Bar navigationBarBackground(Drawable drawable);

    Bar invasionStatusBar();

    Bar statusBarBackground(int statusBarColor);

    Bar navigationBarBackground(int color);

    Bar statusBarBackground(Drawable drawable);

    Bar statusBarBackgroundAlpha(int alpha);

    Bar invasionNavigationBar();

    /**
     * @deprecated use {@link #fitsStatusBarView(int)} instead.
     */
    Bar fitsStatusBarView(int viewId);

    @Deprecated
    Bar fitsSystemWindowView(int viewId);

    Bar fitsStatusBarView(View view);
    /**
     * @deprecated use {@link #fitsStatusBarView(View)} instead.
     */
    @Deprecated
    Bar fitsSystemWindowView(View view);

    Bar fitsNavigationBarView(int viewId);

    Bar fitsNavigationBarView(View view);

    Bar navigationBarBackgroundAlpha(int alpha);

}
