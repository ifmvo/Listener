package com.ifmvo.matthew.base.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ifmvo.matthew.R;
import com.ifmvo.matthew.custom.BottomTabView;

import java.util.List;

/**
 * Created by 陈序员 on 2017/4/27.
 * Email: Matthew_Chen_1994@163.com
 * Blog: https://blog.ifmvo.cn
 */

public abstract class BottomTabBaseActivity extends ExBaseTopBarActivity {

    ViewPager viewPager;
    BottomTabView bottomTabView;
    FragmentPagerAdapter adapter;

    @Override
    public void onCreatedContentView() {
        setMainContentView(R.layout.activity_base_bottom_tab);
    }

    @Override
    public void onFindView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        bottomTabView = (BottomTabView) findViewById(R.id.bottomTabView);

        layTopBar.setVisibility(View.GONE);
        viewPager.setOffscreenPageLimit(3);
        init();
    }

    @Override
    public void onPostLoad() {
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return getFragments().get(position);
            }

            @Override
            public int getCount() {
                return getFragments().size();
            }
        };

        viewPager.setAdapter(adapter);

        if (getCenterView() == null){
            bottomTabView.setTabItemViews(getTabViews());
        }else {
            bottomTabView.setTabItemViews(getTabViews(), getCenterView());
        }

        bottomTabView.setOnTabItemSelectListener(new BottomTabView.OnTabItemSelectListener() {
            @Override
            public void onTabItemSelect(int position) {
                viewPager.setCurrentItem(position, true);
            }
        });

        bottomTabView.setOnSecondSelectListener(new BottomTabView.OnSecondSelectListener() {
            @Override
            public void onSecondSelect(int position) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomTabView.updatePosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    protected abstract List<BottomTabView.TabItemView> getTabViews();
    protected abstract List<Fragment> getFragments();

    protected View getCenterView(){
        return null;
    }

    protected void init(){

    }

}

