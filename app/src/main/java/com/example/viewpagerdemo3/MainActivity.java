package com.example.viewpagerdemo3;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity implements TabHost.TabContentFactory {

    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager viewPager = findViewById(R.id.view_pager);

        //初始化tabHost
        tabHost = findViewById(R.id.tab_host);
        tabHost.setup();

        //初始化数据
        int[] titleIDs = new int[]{
                R.string.alipay,
                R.string.like,
                R.string.card
        };

        int[] drawableIds = new int[]{
                R.drawable.main_icon_alipay,
                R.drawable.main_icon_like,
                R.drawable.main_icon_card
        };

        // data <--> view
        for (int index = 0; index < titleIDs.length; index++) {
            View view = getLayoutInflater().inflate(R.layout.tab_main, null, false);
            ImageView imageView = view.findViewById(R.id.tab_icon);
            TextView textView = view.findViewById(R.id.tab_txt);
            imageView.setImageResource(drawableIds[index]);
            textView.setText(titleIDs[index]);
            tabHost.addTab(
                    tabHost.newTabSpec(getString(titleIDs[index]))
                            .setIndicator(view)
                            .setContent(this)
            );
        }


        //3个Fragment
        final Fragment[] fragments = new Fragment[]{
                FirstFragment.newInstance("home"),
                FirstFragment.newInstance("message"),
                FirstFragment.newInstance("me")
        };
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (tabHost != null) {
                    tabHost.setCurrentTab(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabHost!=null){
                    int position = tabHost.getCurrentTab();
                    viewPager.setCurrentItem(position);
                }
            }
        });
    }

    @Override
    public View createTabContent(String tag) {
        View view = new View(this);
        view.setMinimumHeight(0);
        view.setMinimumWidth(0);
        return view;
    }
}
