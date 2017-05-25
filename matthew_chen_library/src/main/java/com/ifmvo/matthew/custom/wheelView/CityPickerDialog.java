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
//import com.timepost.shiyi.R;
//import com.timepost.shiyi.utils.ViewUtil;
//import com.timepost.shiyi.utils.addr.AddrDBhelper;
//import com.timepost.shiyi.utils.addr.Area;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by ZongfaHe on 16/3/15.
// */
//public class CityPickerDialog extends Dialog {
//    AddrDBhelper addrDBhelper;
//    List<Area> provinceList = new ArrayList<>();
//    List<Area> cityList = new ArrayList<>();
//    List<Area> areaList = new ArrayList<>();
//    View view;
//    WheelView wvProvince, wvCity, wvArea;
//
//    Area provinceArea, cityArea, area;
//
//    public CityPickerDialog(Context context) {
//        super(context, R.style.dialog_picker);
//        init(context);
//    }
//
//    private void init(Context context) {
//        view = LayoutInflater.from(context).inflate(R.layout.dialog_city_picker, null);
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
//        wvProvince = (WheelView) view.findViewById(R.id.province);
//        wvCity = (WheelView) view.findViewById(R.id.city);
//        wvArea = (WheelView) view.findViewById(R.id.area);
//        addrDBhelper = new AddrDBhelper(context);
//        provinceList.addAll(addrDBhelper.getProvince());
//        cityList.addAll(addrDBhelper.getCity(provinceList.get(0).getCode()));
//        areaList.addAll(addrDBhelper.getDistrict(cityList.get(0).getCode()));
//        provinceArea = provinceList.get(0);
//        cityArea = cityList.get(0);
//        area = areaList.get(0);
//        wvProvince.setAdapter(new ProvinceAdapter());
//        wvCity.setAdapter(new CityAdapter());
//        wvArea.setAdapter(new AreaAdapter());
//        wvProvince.addChangingListener(new OnWheelChangedListener() {
//            @Override
//            public void onChanged(WheelView wheel, int oldValue, int newValue) {
//                provinceArea = provinceList.get(newValue);
//                cityList.clear();
//                cityList.addAll(addrDBhelper.getCity(provinceList.get(newValue).getCode()));
//                wvCity.setAdapter(new CityAdapter());
//                wvCity.setCurrentItem(0);
//                areaList.clear();
//                areaList.addAll(addrDBhelper.getDistrict(cityList.get(0).getCode()));
//                wvArea.setAdapter(new AreaAdapter());
//                wvArea.setCurrentItem(0);
//                cityArea = cityList.get(0);
//                area = areaList.get(0);
//            }
//        });
//        wvCity.addChangingListener(new OnWheelChangedListener() {
//            @Override
//            public void onChanged(WheelView wheel, int oldValue, int newValue) {
//                cityArea = cityList.get(newValue);
//                areaList.clear();
//                areaList.addAll(addrDBhelper.getDistrict(cityList.get(newValue).getCode()));
//                wvArea.setAdapter(new AreaAdapter());
//                wvArea.setCurrentItem(0);
//                area = areaList.get(0);
//            }
//        });
//        wvArea.addChangingListener(new OnWheelChangedListener() {
//            @Override
//            public void onChanged(WheelView wheel, int oldValue, int newValue) {
//                area = areaList.get(newValue);
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
//                    mSelectListener.onSelect(provinceArea, cityArea, area);
//                }
//                dismiss();
//            }
//        });
//    }
//
//    class ProvinceAdapter implements WheelAdapter {
//
//        @Override
//        public int getItemsCount() {
//            return provinceList.size();
//        }
//
//        @Override
//        public String getItem(int index) {
//            return provinceList.get(index).getName();
//        }
//
//        @Override
//        public int getMaximumLength() {
//            return 100;
//        }
//    }
//
//    class CityAdapter implements WheelAdapter {
//
//        @Override
//        public int getItemsCount() {
//            return cityList.size();
//        }
//
//        @Override
//        public String getItem(int index) {
//            if (cityList.size() > index)
//                return cityList.get(index).getName();
//            else
//                return "";
//        }
//
//        @Override
//        public int getMaximumLength() {
//            return 100;
//        }
//    }
//
//    class AreaAdapter implements WheelAdapter {
//
//        @Override
//        public int getItemsCount() {
//            return areaList.size();
//        }
//
//        @Override
//        public String getItem(int index) {
//            if (areaList.size() > index)
//                return areaList.get(index).getName();
//            else
//                return "";
//        }
//
//        @Override
//        public int getMaximumLength() {
//            return 100;
//        }
//    }
//
//    SelectListener mSelectListener;
//
//    public void setSelectListener(SelectListener selectListener) {
//        mSelectListener = selectListener;
//    }
//
//    public interface SelectListener {
//        void onSelect(Area province, Area city, Area area);
//    }
//}
