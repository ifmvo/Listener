package com.ifmvo.matthew.custom.banner;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ifmvo.matthew.R;
import com.ifmvo.matthew.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Description
 */
public class LayAdImg extends LinearLayout {
    Context context;
    AutoScrollViewPager ad_viewpager;
    ADViewPagerAdapter ad_adapter;
    ArrayList<BannerBean> ad_list;
    LinearLayout pointsLayout;
    ArrayList<ImageView> points;
    RelativeLayout layAd;
    private int adIndex = 0;

    public LayAdImg(Context context) {
        super(context);
        init(context);
    }

    public LayAdImg(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.ad_head, this);
        initHead();
    }

    public void setADHeight(int height) {
        layAd.getLayoutParams().height = height;
    }

    public void setData(List<BannerBean> sliders, int defDrawable) {
        ad_list.clear();
        if (sliders == null) {
            if (defDrawable != 0) {
                layAd.setBackgroundResource(defDrawable);
            } else {
                layAd.setBackgroundResource(0);
            }
            ad_adapter.notifyDataSetChanged();
            initPoints();
            return;
        }
        if (sliders.size() > 0) {
            layAd.setBackgroundResource(0);
            ad_list.addAll(sliders);
        } else {
            if (defDrawable != 0) {
                layAd.setBackgroundResource(defDrawable);
            } else {
                layAd.setBackgroundResource(0);
            }
        }
        ad_adapter.notifyDataSetChanged();
        initPoints();
    }

    public void setData(List<BannerBean> sliders) {
        setData(sliders, 0);
    }

    /**
     * 界面可见
     */
    public void startScroll() {
        if (ad_viewpager != null) {
            ad_viewpager.startAutoScroll(3500);
        }
    }

    /**
     * 界面不可见
     */
    public void stopScroll() {
        if (ad_viewpager != null) {
            ad_viewpager.stopAutoScroll();
        }
    }

    private void initHead() {
        // LinearLayout headlay = (LinearLayout)LayoutInflater.from(baseActivity).inflate(R.layout.ad_head, null);
        // ((LinearLayout)findViewById(R.id.layHomeAd)).addView(headlay);
        // LayoutInflater.from(baseActivity).inflate(R.layout.ad_head, ((LinearLayout)findViewById(R.id.layHomeAd)));

        layAd = (RelativeLayout) findViewById(R.id.layAd);
        ad_viewpager = (AutoScrollViewPager) findViewById(R.id.ad_head_viewpager);
        LayoutParams params =
                new LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        ViewUtil.dip2px(context, 190));
        layAd.setLayoutParams(params);
        pointsLayout = (LinearLayout) findViewById(R.id.points);
        points = new ArrayList<>();
        ad_list = new ArrayList<>();
        ad_adapter = new ADViewPagerAdapter(ad_list, context);
        ad_viewpager.setAdapter(ad_adapter);
        ad_viewpager.setInterval(3500);// 设置自动滚动的间隔时间，单位为毫秒
        ad_viewpager.setOnPageChangeListener(new PosterPageChange());
        // ad_viewpager.setDirection(AutoScrollViewPager.RIGHT);//
        // 设置自动滚动的方向，默认向右
        // ad_viewpager.setCycle(true);// 是否自动循环轮播，默认为true
        ad_viewpager.setScrollDurationFactor(2.5);//
        // 设置ViewPager滑动动画间隔时间的倍率，达到减慢动画或改变动画速度的效果
        ad_viewpager.setStopScrollWhenTouch(false);
        // 当手指碰到ViewPager时是否停止自动滚动，默认为true
        ad_viewpager.setSlideBorderMode(AutoScrollViewPager.SLIDE_BORDER_MODE_NONE);//
        // 滑动到第一个或最后一个Item的处理方式，支持没有任何操作、轮播以及传递到父View三种模式
        // ad_viewpager.setBorderAnimation(true);//
        // 设置循环滚动时滑动到从边缘滚动到下一个是否需要动画，默认为true
        ad_viewpager.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ad_viewpager.stopAutoScroll();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        ad_viewpager.stopAutoScroll();
                        break;
                    case MotionEvent.ACTION_UP:
                        ad_viewpager.startAutoScroll();
                        break;

                    default:
                        break;
                }
                return false;
            }
        });
        ad_viewpager.startAutoScroll(3500);

    }

    private void initPoints() {
        points.clear();
        pointsLayout.removeAllViews();
        LayoutParams lp =
                new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT);
        lp.setMargins(ViewUtil.dip2px(context, 3),
                0,
                ViewUtil.dip2px(context, 3),
                ViewUtil.dip2px(context, 6));
        for (int i = 0; i < ad_list.size(); i++) {
            // 添加标记点
            ImageView point = new ImageView(context);
            if (i == adIndex % ad_list.size()) {
                point.setBackgroundResource(R.drawable.shape_oval_banner_in);
            } else {
                point.setBackgroundResource(R.drawable.shape_oval_banner_out);
            }
            point.setLayoutParams(lp);
            points.add(point);
            pointsLayout.addView(point);
        }
    }

    class PosterPageChange implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            adIndex = position;
            for (int i = 0; i < ad_list.size(); i++) {
                points.get(i).setBackgroundResource(R.drawable.shape_oval_banner_out);
            }
           points.get(position % ad_list.size()).setBackgroundResource(R.drawable.shape_oval_banner_in);
        }

    }
}
