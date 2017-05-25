package com.ifmvo.matthew.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifmvo.matthew.R;
import com.ifmvo.matthew.base.bean.BaseBean;
import com.ifmvo.matthew.utils.ViewUtil;

import java.util.ArrayList;

public class SimpleViewPagerIndicator extends LinearLayout {

    /**
     * 指示器的颜色
     */
    private int mIndicatorColor = R.color.theme;
    private String indicatorColor = "#6cc5a3";
    /**
     * 指示器的高度
     */
    private int indicatorH = 3;
    /**
     * 指示器的类型
     */
    public static final int TYPE_TEXT = 0;
    public static final int TYPE_DRAWABLE = 1;
    private int type = 0;

    private ArrayList<SelectItem> selectItems = new ArrayList<>();
    /**
     * 指示器的个数
     */
    private int mTabCount;
    private float mTranslationX;
    private Paint mPaint = new Paint();
    /**
     * 每个tab的宽度
     */
    private int mTabWidth;

    /**
     * 指示器文字的颜色
     */
    private static final int COLOR_TXT_DEF = R.color.gray_a2a2a2;
    private static final int COLOR_TXT_PRESS = R.color.black;


    public SimpleViewPagerIndicator(Context context) {
        this(context, null);
    }

    public SimpleViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
//        mPaint.setColor(getResources().getColor(mIndicatorColor, null));
        mPaint.setColor(Color.parseColor(indicatorColor));
        mPaint.setStrokeWidth(ViewUtil.dip2px(context, indicatorH));
        this.setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        this.h = h;
        if (mTabCount != 0)
            mTabWidth = w / mTabCount;
    }

    public void setSelectItems(int type, ArrayList<SelectItem> selectItems) {
        this.type = type;
        this.selectItems = selectItems;
        mTabCount = selectItems.size();
        generateTitleView();
    }

    public void setIndicatorColor(int indicatorColor) {
        this.mIndicatorColor = indicatorColor;
        generateTitleView();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
        canvas.translate(mTranslationX, getHeight() - 2);
        canvas.drawLine(mTabWidth / 4, 0, mTabWidth * 3 / 4, 0, mPaint);
        canvas.restore();
    }

    public void scroll(int position, float offset) {
        mTranslationX = getWidth() / mTabCount * (position + offset);
        invalidate();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    private void generateTitleView() {
        if (getChildCount() > 0)
            this.removeAllViews();
        int count = selectItems.size();

        setWeightSum(count);
        for (int i = 0; i < count; i++) {
            if (type == TYPE_TEXT)
                addText(count, i);
            else addDrawable(count, i);

        }
    }

    private void addText(int count, int i) {
        final TextView tv = new TextView(getContext());
        tv.setTag("title_" + i);
        LayoutParams lp = new LayoutParams(40, LayoutParams.MATCH_PARENT);
        lp.weight = 1;
        tv.setGravity(Gravity.CENTER);
//        tv.setBackgroundColor(Color.WHITE);
        int[] attrsArray = { android.R.attr.selectableItemBackground };
        // TypedArray typedArray = this.obtainStyledAttributes(attrsArray); //Activity中使用
        TypedArray typedArray = getContext().obtainStyledAttributes(attrsArray);//Fragment中
        int selector = typedArray.getResourceId(0, attrsArray[0]);
        tv.setBackgroundResource(selector);
        // don't forget the resource recycling
        typedArray.recycle();

        tv.setText(selectItems.get(i).getTitle());
        //字体
        tv.setTypeface(Typeface.DEFAULT_BOLD);
        //字大小
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        //第一个是press , 其他的都是default
        if (i == 0) {
            tv.setTextColor(getResources().getColor(COLOR_TXT_PRESS));
        } else {
            tv.setTextColor(getResources().getColor(COLOR_TXT_DEF));
        }
        tv.setLayoutParams(lp);
        final int finalI = i;
        final int finalCount = count;
        tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int j = 0; j < finalCount; j++) {
                    TextView textView = (TextView) findViewWithTag("title_" + j);
                    if (finalI != j) {
                        textView.setTextColor(getResources().getColor(COLOR_TXT_DEF));
                    }
                }
                tv.setTextColor(getResources().getColor(COLOR_TXT_PRESS));
                if (onTabSelect != null) {
                    onTabSelect.onSelect(finalI);
                }
            }
        });
        addView(tv);
    }

    private void addDrawable(int count, int i) {
        final ImageView iv = new ImageView(getContext());
        iv.setTag("title_" + i);
        LayoutParams lp = new LayoutParams(40,
                LayoutParams.MATCH_PARENT);
        lp.weight = 1;
        iv.setScaleType(ImageView.ScaleType.CENTER);
        iv.setBackgroundColor(Color.WHITE);
        if (i == 0) {
            iv.setImageResource(selectItems.get(i).getDrawaleIdPress());
        } else {
            lp.setMargins(ViewUtil.dip2px(getContext(), 1), 0, 0, 0);
            iv.setImageResource(selectItems.get(i).getDrawaleIdDef());
        }
        iv.setLayoutParams(lp);
        final int finalI = i;
        final int finalCount = count;
        iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int j = 0; j < finalCount; j++) {
                    ImageView imageV = (ImageView) findViewWithTag("title_" + j);
                    if (finalI != j) {
                        imageV.setImageResource(selectItems.get(j).getDrawaleIdDef());
                    }
                }
                iv.setImageResource(selectItems.get(finalI).getDrawaleIdPress());
                if (onTabSelect != null) {
                    onTabSelect.onSelect(finalI);
                }
            }
        });
        addView(iv);
    }

    public void onSelect(int position) {
        int count = selectItems.size();
        for (int j = 0; j < count; j++) {
            if (type == TYPE_TEXT) {
                TextView textView = (TextView) findViewWithTag("title_" + j);
                if (position != j) {
                    textView.setTextColor(getResources().getColor(COLOR_TXT_DEF));
                } else {
                    textView.setTextColor(getResources().getColor(COLOR_TXT_PRESS));
                }
            } else {
                ImageView imageV = (ImageView) findViewWithTag("title_" + j);
                if (position != j) {
                    imageV.setImageResource(selectItems.get(j).getDrawaleIdDef());
                } else {
                    imageV.setImageResource(selectItems.get(j).getDrawaleIdPress());
                }
            }
        }
    }

    private onTabSelect onTabSelect;

    public void setOnTabSelect(SimpleViewPagerIndicator.onTabSelect onTabSelect) {
        this.onTabSelect = onTabSelect;
    }

    public interface onTabSelect {
        void onSelect(int positon);
    }


    public static class SelectItem extends BaseBean {
        public SelectItem(String title, int drawaleIdDef, int drawaleIdPress) {
            this.title = title;
            this.drawaleIdDef = drawaleIdDef;
            this.drawaleIdPress = drawaleIdPress;
        }

        private String title;
        private int drawaleIdDef;
        private int drawaleIdPress;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getDrawaleIdDef() {
            return drawaleIdDef;
        }

        public void setDrawaleIdDef(int drawaleIdDef) {
            this.drawaleIdDef = drawaleIdDef;
        }

        public int getDrawaleIdPress() {
            return drawaleIdPress;
        }

        public void setDrawaleIdPress(int drawaleIdPress) {
            this.drawaleIdPress = drawaleIdPress;
        }
    }
}
