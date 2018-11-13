package com.example.jinhui.statusbardemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jinhui.statusbardemo.fragment.FragmentActivity;
import com.yanzhenjie.sofia.Sofia;

import java.util.Arrays;
import java.util.List;

/**
 * https://github.com/yanzhenjie/Sofia
 * Android沉浸式效果的实现，状态栏和导航栏均支持设置颜色、渐变色、图片、透明度、内容入侵和状态栏深色字体；兼容竖屏、横屏，当屏幕旋转时会自动适配。
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Adapter adapter = new Adapter(Arrays.asList(getResources().getStringArray(R.array.main_item)));
        recyclerView.setAdapter(adapter);

        adapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0: {
                        startActivity(new Intent(MainActivity.this, CommonActivity.class));
                        break;
                    }
                    case 1: {
                        startActivity(new Intent(MainActivity.this, GoodsDetailsActivity.class));
                        break;
                    }
                    case 2: {
                        startActivity(new Intent(MainActivity.this, DarkFontStatusActivity.class));
                        break;
                    }
                    case 3: {
                        startActivity(new Intent(MainActivity.this, DrawerLayoutActivity.class));
                        break;
                    }
                    case 4: {
                        startActivity(new Intent(MainActivity.this, FragmentActivity.class));
                        break;
                    }
                }
            }
        });

        Sofia.with(this)
                .statusBarBackground(ContextCompat.getDrawable(this, R.drawable.status_image))
                .navigationBarBackground(ContextCompat.getDrawable(this, R.drawable.navigation_image_a));

    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder> {

        private List<String> mStringList;
        private OnItemClickListener mItemClickListener;

        public void setItemClickListener(OnItemClickListener itemClickListener) {
            this.mItemClickListener = itemClickListener;
        }

        public Adapter(List<String> stringList) {
            mStringList = stringList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false));
            viewHolder.mItemClickListener = mItemClickListener;
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
            viewHolder.mTextView.setText(mStringList.get(position));
        }

        @Override
        public int getItemCount() {
            return mStringList.size();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mTextView;
        OnItemClickListener mItemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.tv_title);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null)
                mItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }
}
