package com.ifmvo.matthew.custom.wheelView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ifmvo.matthew.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class DatePicker {

    private View view;
    private WheelView wv_year;
    private WheelView wv_month;
    private WheelView wv_day;
    private WheelView wv_hours;
    private WheelView wv_mins;
    public int screenheight;
    private boolean hasSelectTime;
    private boolean hasSelectDay;
    private int start_year = 1900, end_year = 2010;
//    private int start_month = 1;
//    private int start_day = 1;
    private long start_time;
    private long end_time;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public int getStart_year() {
        return start_year;
    }

    public void setStart_year(int sTART_YEAR) {
        start_year = sTART_YEAR;
    }

    public int getEnd_year() {
        return end_year;
    }

    public void setEnd_year(int eND_YEAR) {
        end_year = eND_YEAR;
    }

    public DatePicker(View view) {
        super();
        this.view = view;
        hasSelectTime = false;
        hasSelectDay = true;
        setView(view);
    }

    public DatePicker(View view, boolean hasSelectTime, boolean hasSelectDay) {
        super();
        this.view = view;
        this.hasSelectTime = hasSelectTime;
        this.hasSelectDay = hasSelectDay;
        setView(view);
    }

    public void initDateTimePicker(int year, int month) {
        this.initDateTimePicker(year, month, 0, 0, 0);
    }

    public void initDateTimePicker(int year, int month, int day) {
        this.initDateTimePicker(year, month, day, 0, 0);
    }

    /**
     * @Description: TODO 弹出日期时间选择器
     */
    public void initDateTimePicker(int year, int month, int day, int h, int m) {
        // int year = calendar.get(Calendar.YEAR);
        // int month = calendar.get(Calendar.MONTH);
        // int day = calendar.get(Calendar.DATE);
        // 添加大小月月份并将其转换为list,方便之后的判断
        String[] months_big = {"1", "3", "5", "7", "8", "10", "12"};
        String[] months_little = {"4", "6", "9", "11"};

        final List<String> list_big = Arrays.asList(months_big);
        final List<String> list_little = Arrays.asList(months_little);

        // 年
        wv_year = (WheelView) view.findViewById(R.id.year);
        wv_year.setAdapter(new NumericWheelAdapter(start_year, end_year, 1));// 设置"年"的显示数据
        wv_year.setCyclic(false);// 可循环滚动
//        wv_year.setLabel("年");// 添加文字
        wv_year.setCurrentItem(year - start_year);// 初始化时显示的数据

        // 月
        wv_month = (WheelView) view.findViewById(R.id.month);
        wv_month.setAdapter(new NumericWheelAdapter(1, 12, 1));
        wv_month.setCyclic(true);
//        wv_month.setLabel("月");
        wv_month.setCurrentItem(month);

        // 日
        wv_day = (WheelView) view.findViewById(R.id.day);
        wv_day.setCyclic(true);
        // 判断大小月及是否闰年,用来确定"日"的数据
        if (list_big.contains(String.valueOf(month + 1))) {
            wv_day.setAdapter(new NumericWheelAdapter(1, 31, 1));
        } else if (list_little.contains(String.valueOf(month + 1))) {
            wv_day.setAdapter(new NumericWheelAdapter(1, 30,1));
        } else {
            // 闰年
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                wv_day.setAdapter(new NumericWheelAdapter(1, 29, 1));
            else
                wv_day.setAdapter(new NumericWheelAdapter(1, 28, 1));
        }
//        wv_day.setLabel("日");
        wv_day.setCurrentItem(day - 1);

        wv_hours = (WheelView) view.findViewById(R.id.hours);
        wv_mins = (WheelView) view.findViewById(R.id.min);
        if (hasSelectDay) {
            if (hasSelectTime) {
                wv_hours.setVisibility(View.VISIBLE);
                wv_mins.setVisibility(View.VISIBLE);

                wv_hours.setAdapter(new NumericWheelAdapter(0, 23, 1));
                wv_hours.setCyclic(true);// 可循环滚动
//                wv_hours.setLabel("时");// 添加文字
                wv_hours.setCurrentItem(h);

                wv_mins.setAdapter(new NumericWheelAdapter(0, 59, 1));
                wv_mins.setCyclic(true);// 可循环滚动
//                wv_mins.setLabel("分");// 添加文字
                wv_mins.setCurrentItem(m);
            } else {
                wv_hours.setVisibility(View.GONE);
                wv_mins.setVisibility(View.GONE);
            }
        } else {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 3.0f);
            wv_year.setLayoutParams(lp);
            wv_day.setVisibility(View.GONE);
            wv_hours.setVisibility(View.GONE);
            wv_mins.setVisibility(View.GONE);
        }

        // 添加"年"监听
        OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                int year_num = newValue + start_year;
                // 判断大小月及是否闰年,用来确定"日"的数据
                if (list_big
                        .contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 31,1));
                } else if (list_little.contains(String.valueOf(wv_month
                        .getCurrentItem() + 1))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 30,1));
                } else {
                    if ((year_num % 4 == 0 && year_num % 100 != 0)
                            || year_num % 400 == 0)
                        wv_day.setAdapter(new NumericWheelAdapter(1, 29,1));
                    else
                        wv_day.setAdapter(new NumericWheelAdapter(1, 28,1));
                }

//                FQL.e("年", "wheel.getLabel():"+wheel.getLabel() + ",year_num:" + year_num);
            }
        };
        // 添加"月"监听
        OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                int month_num = newValue + 1;
                // 判断大小月及是否闰年,用来确定"日"的数据
                if (list_big.contains(String.valueOf(month_num))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 31, 1));
                } else if (list_little.contains(String.valueOf(month_num))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 30, 1));
                } else {
                    if (((wv_year.getCurrentItem() + start_year) % 4 == 0 && (wv_year
                            .getCurrentItem() + start_year) % 100 != 0)
                            || (wv_year.getCurrentItem() + start_year) % 400 == 0)
                        wv_day.setAdapter(new NumericWheelAdapter(1, 29, 1));
                    else
                        wv_day.setAdapter(new NumericWheelAdapter(1, 28, 1));
                }


                // 监听到,判断是否符合传过来的值
//                FQL.e("月", "wheel.getLabel():"+wheel.getLabel() + ",oldValue:" + oldValue + ",newValue" + newValue);

            }
        };
        wv_year.addChangingListener(wheelListener_year);
        wv_month.addChangingListener(wheelListener_month);

//        // 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
//        int textSize = 0;
//        if (hasSelectDay) {
//            if (hasSelectTime)
//                textSize = (screenheight / 100) * 3;
//            else
//                textSize = (screenheight / 100) * 4;
//        } else {
//            textSize = (screenheight / 100) * 3;
//        }
//        wv_day.TEXT_SIZE = textSize;
//        wv_day.ITEMS_TEXT_SIZE=textSize;
//        wv_month.TEXT_SIZE = textSize;
//        wv_month.ITEMS_TEXT_SIZE=textSize;
//        wv_year.TEXT_SIZE = textSize;
//        wv_year.ITEMS_TEXT_SIZE=textSize;
//        wv_hours.TEXT_SIZE = textSize;
//        wv_hours.ITEMS_TEXT_SIZE=textSize;
//        wv_mins.TEXT_SIZE = textSize;
//        wv_mins.ITEMS_TEXT_SIZE=textSize;
    }

    public Date getTime() throws ParseException {
        StringBuffer sb = new StringBuffer();
        String month = wv_month.getCurrentItem() + 1 + "";
        String day = wv_day.getCurrentItem() + 1 + "";
        if (wv_month.getCurrentItem() + 1 < 10) {
            month = "0" + month;
        }
        if (wv_day.getCurrentItem() + 1 < 10) {
            day = "0" + day;
        }
        if (hasSelectDay) {
            if (!hasSelectTime) {
                sb.append((wv_year.getCurrentItem() + start_year)).append("-")
                        .append(month).append("-")
                        .append(day);
                return new SimpleDateFormat("yyyy-MM-dd").parse(sb.toString());
            } else {
                String hours = wv_hours.getCurrentItem() + "";
                String mins = wv_mins.getCurrentItem() + "";
                if (wv_hours.getCurrentItem() < 10) {
                    hours = "0" + hours;
                }
                if (wv_mins.getCurrentItem() < 10) {
                    mins = "0" + mins;
                }
                sb.append((wv_year.getCurrentItem() + start_year)).append("-")
                        .append(month).append("-")
                        .append(day).append(" ")
                        .append(hours).append(":")
                        .append(mins).append(":00");
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sb.toString());
            }
        } else {
            sb.append((wv_year.getCurrentItem() + start_year)).append("-")
                    .append(month).append("-")
                    .append(day);
            return new SimpleDateFormat("yyyy-MM").parse(sb.toString());
        }
    }
}
