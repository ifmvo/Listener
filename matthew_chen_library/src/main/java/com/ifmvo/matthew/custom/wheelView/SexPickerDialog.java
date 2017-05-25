//package com.ifmvo.matthew.custom.wheelView;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Context;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.Window;
//
//import com.ifmvo.matthew.R;
//import com.ifmvo.matthew.base.bean.BaseBean;
//import com.ifmvo.matthew.utils.ViewUtil;
//
//import java.util.ArrayList;
//
///**
// * Created by ZongfaHe on 16/3/15.
// */
//public class SexPickerDialog extends Dialog {
//    ArrayList<Sex> list = new ArrayList<>();
//    View view;
//    WheelView wvSex;
//    Sex sex;
//
//    public SexPickerDialog(Context context) {
//        super(context, R.style.dialog_picker);
//        init(context);
//    }
//
//    private void init(Context context) {
//        view = LayoutInflater.from(context).inflate(R.layout.dialog_sex_picker, null);
//        view.setMinimumWidth(ViewUtil.getScreenDM((Activity) context).widthPixels);
////        DatePicker.setEnd_year(endYear);
////        DatePicker.setStart_year(startYear);
////        ScreenInfo screenInfo = new ScreenInfo((BaseActivity) context);
////        final DatePicker wheelMain = new DatePicker(timepickerview, format.equals(FORMAT_MINU), !format.equals(FORMAT_MONTH));
////        wheelMain.screenheight = screenInfo.getHeight();
//        Window window = getWindow();
//        window.setContentView(view);
//        window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
//
//        wvSex = (WheelView) view.findViewById(R.id.sex);
//        sex = new Sex(1, "男");
//        list.add(new Sex(1, "男"));
//        list.add(new Sex(0, "女"));
//        wvSex.setAdapter(new SexAdapter());
//        wvSex.addChangingListener(new OnWheelChangedListener() {
//            @Override
//            public void onChanged(WheelView wheel, int oldValue, int newValue) {
//                FQL.d("xxx", "new Value===" + newValue);
//                sex = list.get(newValue);
//            }
//        });
//        view.findViewById(R.id.btnCancle).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
//        view.findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mSelectListener != null) {
//                    mSelectListener.onSelect(sex);
//                }
//                dismiss();
//            }
//        });
//    }
//
//    class SexAdapter implements WheelAdapter {
//
//        @Override
//        public int getItemsCount() {
//            return list.size();
//        }
//
//        @Override
//        public String getItem(int index) {
//            return list.get(index).getName();
//        }
//
//        @Override
//        public int getMaximumLength() {
//            return 100;
//        }
//    }
//
//
//    SelectListener mSelectListener;
//
//    public void setSelectListener(SelectListener selectListener) {
//        mSelectListener = selectListener;
//    }
//
//    public interface SelectListener {
//        void onSelect(Sex sex);
//    }
//
//    public class Sex extends BaseBean {
//        public Sex(long id, String name) {
//            setId(id);
//            this.name = name;
//        }
//
//        private String name;
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//    }
//}
