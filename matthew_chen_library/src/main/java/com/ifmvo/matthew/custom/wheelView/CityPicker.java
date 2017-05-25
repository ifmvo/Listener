//package com.ifmvo.matthew.custom.wheelView;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.Window;
//import android.widget.Button;
//
//import com.ifmvo.matthew.R;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//
//public class CityPicker {
//    private Activity ct;
//    private View view;
//    private Dialog dialog;
//    private Button sumbit;
//    private WheelView wvProvince;
//    private WheelView wvCity;
//    private WheelView wvArea;
//    private List<String[]> listProvince;
//    private List<String[]> listCity;
//    private String province;
//    private String city;
//int screenheight;
//
//    public CityPicker(Activity ct) {
//        this.ct = ct;
//        LayoutInflater inflater = LayoutInflater.from(ct);
//        view = inflater.inflate(R.layout.dialog_city_picker, null);
//        view.setMinimumWidth(ct.getWindowManager().getDefaultDisplay()
//                .getWidth());
//        ScreenInfo screenInfo = new ScreenInfo(ct);
//        screenheight = screenInfo.getHeight();
//        wvProvince = (WheelView) view.findViewById(R.id.province);
//        wvCity = (WheelView) view.findViewById(R.id.city);
//        wvArea = (WheelView) view.findViewById(R.id.area);
//        initCitys();
//        listProvince = new ArrayList<String[]>();
//        for (String[] city : citys) {
//            if ("1".equals(city[1])) {
//                listProvince.add(city);
//            }
//        }
//
//        String code = listProvince.get(0)[0].substring(0, 3);
//        listCity = new ArrayList<String[]>();
//        for (String[] city : citys) {
//            if ("2".equals(city[1]) && city[0].startsWith(code)) {
//                listCity.add(city);
//            }
//        }
//        province = listProvince.get(0)[3];
//        city = listCity.get(0)[3];
//
//        wvProvince.setAdapter(new WheelAdapter() {
//
//            @Override
//            public int getMaximumLength() {
//                // TODO Auto-generated method stub
//                return 100;
//            }
//
//            @Override
//            public int getItemsCount() {
//                return listProvince.size();
//            }
//
//            @Override
//            public String getItem(int index) {
//                return listProvince.get(index)[3];
//            }
//        });
//
//        final WheelAdapter cityAdapter = new WheelAdapter() {
//
//            @Override
//            public int getMaximumLength() {
//                // TODO Auto-generated method stub
//                return 100;
//            }
//
//            @Override
//            public int getItemsCount() {
//                return listCity.size();
//            }
//
//            @Override
//            public String getItem(int index) {
//                return listCity.get(index)[3];
//            }
//        };
//        wvCity.setAdapter(cityAdapter);
//        wvProvince.addChangingListener(new OnWheelChangedListener() {
//
//            @Override
//            public void onChanged(WheelView wheel, int oldValue, int newValue) {
//                String[] p = listProvince.get(newValue);
//                String code = p[0].substring(0, 3);
//                listCity = new ArrayList<String[]>();
//                for (String[] city : citys) {
//                    if ("2".equals(city[1]) && city[0].startsWith(code)) {
//                        listCity.add(city);
//                    }
//                }
//                province = p[3];
//                city = listCity.get(0)[3];
//                wvCity.setAdapter(cityAdapter);
//                wvCity.setCurrentItem(0);
//            }
//        });
//
//        wvCity.addChangingListener(new OnWheelChangedListener() {
//
//            @Override
//            public void onChanged(WheelView wheel, int oldValue, int newValue) {
//                city = listCity.get(newValue)[3];
//            }
//        });
//
//        sumbit = (Button) view.findViewById(R.id.btnOk);
//        view.findViewById(R.id.btnCancle).setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
////        int textsize= (screenheight / 100) * 4;
////        wvArea.ITEMS_TEXT_SIZE=textsize;
////        wvArea.TEXT_SIZE=textsize;
////        wvCity.ITEMS_TEXT_SIZE=textsize;
////        wvCity.TEXT_SIZE=textsize;
////        wvProvince.ITEMS_TEXT_SIZE=textsize;
////        wvProvince.TEXT_SIZE=textsize;
//
//    }
//
//    public interface PickerListner {
//        void OnPick(String p, String c);
//    }
//
//    public void show() {
//        dialog = new AlertDialog.Builder(ct).create();
//        dialog.show();
//        Window window = dialog.getWindow();
//        window.setContentView(view);
//        window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
//    }
//
//    public void setPickerListner(final PickerListner l) {
//        if (null == l) {
//            return;
//        }
//        sumbit.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                l.OnPick(province, city);
//                dialog.dismiss();
//            }
//        });
//    }
//
//
//    List<String[]> citys = new ArrayList<String[]>();
//
//    private void initCitys() {
//        citys.add(new String[]{"1000000", "1", "", "北京", "BJ"});
//        citys.add(new String[]{"1000050", "2", "1000000", "东城", "DC"});
//        citys.add(new String[]{"1000100", "2", "1000000", "西城", "XC"});
//        citys.add(new String[]{"1000150", "2", "1000000", "朝阳", "CY"});
//        citys.add(new String[]{"1000200", "2", "1000000", "丰台", "FT"});
//        citys.add(new String[]{"1000250", "2", "1000000", "石景山", "SJS"});
//        citys.add(new String[]{"1000300", "2", "1000000", "海淀", "HD"});
//        citys.add(new String[]{"1000350", "2", "1000000", "门头沟", "MTG"});
//        citys.add(new String[]{"1000400", "2", "1000000", "房山", "FS"});
//        citys.add(new String[]{"1000450", "2", "1000000", "通州", "TZ"});
//        citys.add(new String[]{"1000500", "2", "1000000", "顺义", "SY"});
//        citys.add(new String[]{"1000550", "2", "1000000", "昌平", "CP"});
//        citys.add(new String[]{"1000600", "2", "1000000", "大兴", "DX"});
//        citys.add(new String[]{"1000650", "2", "1000000", "怀柔", "HR"});
//        citys.add(new String[]{"1000700", "2", "1000000", "平谷", "PG"});
//        citys.add(new String[]{"1000750", "2", "1000000", "密云县", "MYX"});
//        citys.add(new String[]{"1000800", "2", "1000000", "延庆县", "YQX"});
//        citys.add(new String[]{"1010000", "1", "", "天津", "TJ"});
//        citys.add(new String[]{"1010050", "2", "1010000", "和平", "HP"});
//        citys.add(new String[]{"1010100", "2", "1010000", "河东", "HD"});
//        citys.add(new String[]{"1010150", "2", "1010000", "河西", "HX"});
//        citys.add(new String[]{"1010200", "2", "1010000", "南开", "NK"});
//        citys.add(new String[]{"1010250", "2", "1010000", "河北", "HB"});
//        citys.add(new String[]{"1010300", "2", "1010000", "红桥", "HQ"});
//        citys.add(new String[]{"1010350", "2", "1010000", "东丽", "DL"});
//        citys.add(new String[]{"1010400", "2", "1010000", "西青", "XQ"});
//        citys.add(new String[]{"1010450", "2", "1010000", "津南", "JN"});
//        citys.add(new String[]{"1010500", "2", "1010000", "北辰", "BC"});
//        citys.add(new String[]{"1010550", "2", "1010000", "武清", "WQ"});
//        citys.add(new String[]{"1010600", "2", "1010000", "宝坻", "BC"});
//        citys.add(new String[]{"1010650", "2", "1010000", "滨海", "BH"});
//        citys.add(new String[]{"1010700", "2", "1010000", "宁河县", "NHX"});
//        citys.add(new String[]{"1010750", "2", "1010000", "静海县", "JHX"});
//        citys.add(new String[]{"1010800", "2", "1010000", "蓟县", "JX"});
//        citys.add(new String[]{"1020000", "1", "", "河北", "HB"});
//        citys.add(new String[]{"1020050", "2", "1020000", "石家庄", "SJZ"});
//        citys.add(new String[]{"1020100", "2", "1020000", "唐山", "TS"});
//        citys.add(new String[]{"1020150", "2", "1020000", "秦皇岛", "QHD"});
//        citys.add(new String[]{"1020200", "2", "1020000", "邯郸", "HD"});
//        citys.add(new String[]{"1020250", "2", "1020000", "邢台", "XT"});
//        citys.add(new String[]{"1020300", "2", "1020000", "保定", "BD"});
//        citys.add(new String[]{"1020350", "2", "1020000", "张家口", "ZJK"});
//        citys.add(new String[]{"1020400", "2", "1020000", "承德", "CD"});
//        citys.add(new String[]{"1020450", "2", "1020000", "沧州", "CZ"});
//        citys.add(new String[]{"1020500", "2", "1020000", "廊坊", "LF"});
//        citys.add(new String[]{"1020550", "2", "1020000", "衡水", "HS"});
//        citys.add(new String[]{"1030000", "1", "", "山西", "SX"});
//        citys.add(new String[]{"1030050", "2", "1030000", "太原", "TY"});
//        citys.add(new String[]{"1030100", "2", "1030000", "大同", "DT"});
//        citys.add(new String[]{"1030150", "2", "1030000", "阳泉", "YQ"});
//        citys.add(new String[]{"1030200", "2", "1030000", "长治", "ZZ"});
//        citys.add(new String[]{"1030250", "2", "1030000", "晋城", "JC"});
//        citys.add(new String[]{"1030300", "2", "1030000", "朔州", "SZ"});
//        citys.add(new String[]{"1030350", "2", "1030000", "晋中", "JZ"});
//        citys.add(new String[]{"1030400", "2", "1030000", "运城", "YC"});
//        citys.add(new String[]{"1030450", "2", "1030000", "忻州", "XZ"});
//        citys.add(new String[]{"1030500", "2", "1030000", "临汾", "LF"});
//        citys.add(new String[]{"1030550", "2", "1030000", "吕梁", "LL"});
//        citys.add(new String[]{"1040000", "1", "", "内蒙古", "NMG"});
//        citys.add(new String[]{"1040050", "2", "1040000", "呼和浩特", "HHHT"});
//        citys.add(new String[]{"1040100", "2", "1040000", "包头", "BT"});
//        citys.add(new String[]{"1040150", "2", "1040000", "乌海", "WH"});
//        citys.add(new String[]{"1040200", "2", "1040000", "赤峰", "CF"});
//        citys.add(new String[]{"1040250", "2", "1040000", "通辽", "TL"});
//        citys.add(new String[]{"1040300", "2", "1040000", "鄂尔多斯", "EEDS"});
//        citys.add(new String[]{"1040350", "2", "1040000", "呼伦贝尔", "HLBE"});
//        citys.add(new String[]{"1040400", "2", "1040000", "巴彦淖尔", "BYNE"});
//        citys.add(new String[]{"1040450", "2", "1040000", "乌兰察布", "WLCB"});
//        citys.add(new String[]{"1040500", "2", "1040000", "兴安", "XA"});
//        citys.add(new String[]{"1040550", "2", "1040000", "锡林郭勒", "XLGL"});
//        citys.add(new String[]{"1040600", "2", "1040000", "阿拉善", "ALS"});
//        citys.add(new String[]{"1050000", "1", "", "辽宁", "LN"});
//        citys.add(new String[]{"1050050", "2", "1050000", "沈阳", "SY"});
//        citys.add(new String[]{"1050100", "2", "1050000", "大连", "DL"});
//        citys.add(new String[]{"1050150", "2", "1050000", "鞍山", "AS"});
//        citys.add(new String[]{"1050200", "2", "1050000", "抚顺", "FS"});
//        citys.add(new String[]{"1050250", "2", "1050000", "本溪", "BX"});
//        citys.add(new String[]{"1050300", "2", "1050000", "丹东", "DD"});
//        citys.add(new String[]{"1050350", "2", "1050000", "锦州", "JZ"});
//        citys.add(new String[]{"1050400", "2", "1050000", "营口", "YK"});
//        citys.add(new String[]{"1050450", "2", "1050000", "阜新", "FX"});
//        citys.add(new String[]{"1050500", "2", "1050000", "辽阳", "LY"});
//        citys.add(new String[]{"1050550", "2", "1050000", "盘锦", "PJ"});
//        citys.add(new String[]{"1050600", "2", "1050000", "铁岭", "TL"});
//        citys.add(new String[]{"1050650", "2", "1050000", "朝阳", "CY"});
//        citys.add(new String[]{"1050700", "2", "1050000", "葫芦岛", "HLD"});
//        citys.add(new String[]{"1060000", "1", "", "吉林", "JL"});
//        citys.add(new String[]{"1060050", "2", "1060000", "长春", "ZC"});
//        citys.add(new String[]{"1060100", "2", "1060000", "吉林", "JL"});
//        citys.add(new String[]{"1060150", "2", "1060000", "四平", "SP"});
//        citys.add(new String[]{"1060200", "2", "1060000", "辽源", "LY"});
//        citys.add(new String[]{"1060250", "2", "1060000", "通化", "TH"});
//        citys.add(new String[]{"1060300", "2", "1060000", "白山", "BS"});
//        citys.add(new String[]{"1060350", "2", "1060000", "松原", "SY"});
//        citys.add(new String[]{"1060400", "2", "1060000", "白城", "BC"});
//        citys.add(new String[]{"1060450", "2", "1060000", "延边", "YB"});
//        citys.add(new String[]{"1070000", "1", "", "黑龙江", "HLJ"});
//        citys.add(new String[]{"1070050", "2", "1070000", "哈尔滨", "HEB"});
//        citys.add(new String[]{"1070100", "2", "1070000", "齐齐哈尔", "QQHE"});
//        citys.add(new String[]{"1070150", "2", "1070000", "鸡西", "JX"});
//        citys.add(new String[]{"1070200", "2", "1070000", "鹤岗", "HG"});
//        citys.add(new String[]{"1070250", "2", "1070000", "双鸭山", "SYS"});
//        citys.add(new String[]{"1070300", "2", "1070000", "大庆", "DQ"});
//        citys.add(new String[]{"1070350", "2", "1070000", "伊春", "YC"});
//        citys.add(new String[]{"1070400", "2", "1070000", "佳木斯", "JMS"});
//        citys.add(new String[]{"1070450", "2", "1070000", "七台河", "QTH"});
//        citys.add(new String[]{"1070500", "2", "1070000", "牡丹江", "MDJ"});
//        citys.add(new String[]{"1070550", "2", "1070000", "黑河", "HH"});
//        citys.add(new String[]{"1070600", "2", "1070000", "绥化", "SH"});
//        citys.add(new String[]{"1070650", "2", "1070000", "大兴安岭", "DXAL"});
//        citys.add(new String[]{"1080000", "1", "", "上海", "SH"});
//        citys.add(new String[]{"1080050", "2", "1080000", "黄浦", "HP"});
//        citys.add(new String[]{"1080100", "2", "1080000", "卢湾", "LW"});
//        citys.add(new String[]{"1080150", "2", "1080000", "徐汇", "XH"});
//        citys.add(new String[]{"1080200", "2", "1080000", "长宁", "ZN"});
//        citys.add(new String[]{"1080250", "2", "1080000", "静安", "JA"});
//        citys.add(new String[]{"1080300", "2", "1080000", "普陀", "PT"});
//        citys.add(new String[]{"1080350", "2", "1080000", "闸北", "ZB"});
//        citys.add(new String[]{"1080400", "2", "1080000", "虹口", "HK"});
//        citys.add(new String[]{"1080450", "2", "1080000", "杨浦", "YP"});
//        citys.add(new String[]{"1080500", "2", "1080000", "闵行", "MX"});
//        citys.add(new String[]{"1080550", "2", "1080000", "宝山", "BS"});
//        citys.add(new String[]{"1080600", "2", "1080000", "嘉定", "JD"});
//        citys.add(new String[]{"1080650", "2", "1080000", "浦东", "PD"});
//        citys.add(new String[]{"1080700", "2", "1080000", "金山", "JS"});
//        citys.add(new String[]{"1080750", "2", "1080000", "松江", "SJ"});
//        citys.add(new String[]{"1080800", "2", "1080000", "青浦", "QP"});
//        citys.add(new String[]{"1080850", "2", "1080000", "奉贤", "FX"});
//        citys.add(new String[]{"1080900", "2", "1080000", "崇明县", "CMX"});
//        citys.add(new String[]{"1090000", "1", "", "江苏", "JS"});
//        citys.add(new String[]{"1090050", "2", "1090000", "南京", "NJ"});
//        citys.add(new String[]{"1090100", "2", "1090000", "无锡", "WX"});
//        citys.add(new String[]{"1090150", "2", "1090000", "徐州", "XZ"});
//        citys.add(new String[]{"1090200", "2", "1090000", "常州", "CZ"});
//        citys.add(new String[]{"1090250", "2", "1090000", "苏州", "SZ"});
//        citys.add(new String[]{"1090300", "2", "1090000", "南通", "NT"});
//        citys.add(new String[]{"1090350", "2", "1090000", "连云港", "LYG"});
//        citys.add(new String[]{"1090400", "2", "1090000", "淮安", "HA"});
//        citys.add(new String[]{"1090450", "2", "1090000", "盐城", "YC"});
//        citys.add(new String[]{"1090500", "2", "1090000", "扬州", "YZ"});
//        citys.add(new String[]{"1090550", "2", "1090000", "镇江", "ZJ"});
//        citys.add(new String[]{"1090600", "2", "1090000", "泰州", "TZ"});
//        citys.add(new String[]{"1090650", "2", "1090000", "宿迁", "SQ"});
//        citys.add(new String[]{"1100000", "1", "", "浙江", "ZJ"});
//        citys.add(new String[]{"1100050", "2", "1100000", "杭州", "HZ"});
//        citys.add(new String[]{"1100100", "2", "1100000", "宁波", "NB"});
//        citys.add(new String[]{"1100150", "2", "1100000", "温州", "WZ"});
//        citys.add(new String[]{"1100200", "2", "1100000", "嘉兴", "JX"});
//        citys.add(new String[]{"1100250", "2", "1100000", "湖州", "HZ"});
//        citys.add(new String[]{"1100300", "2", "1100000", "绍兴", "SX"});
//        citys.add(new String[]{"1100350", "2", "1100000", "金华", "JH"});
//        citys.add(new String[]{"1100400", "2", "1100000", "衢州", "QZ"});
//        citys.add(new String[]{"1100450", "2", "1100000", "舟山", "ZS"});
//        citys.add(new String[]{"1100500", "2", "1100000", "台州", "TZ"});
//        citys.add(new String[]{"1100550", "2", "1100000", "丽水", "LS"});
//        citys.add(new String[]{"1110000", "1", "", "安徽", "AH"});
//        citys.add(new String[]{"1110050", "2", "1110000", "合肥", "HF"});
//        citys.add(new String[]{"1110100", "2", "1110000", "芜湖", "WH"});
//        citys.add(new String[]{"1110150", "2", "1110000", "蚌埠", "BB"});
//        citys.add(new String[]{"1110200", "2", "1110000", "淮南", "HN"});
//        citys.add(new String[]{"1110250", "2", "1110000", "马鞍山", "MAS"});
//        citys.add(new String[]{"1110300", "2", "1110000", "淮北", "HB"});
//        citys.add(new String[]{"1110350", "2", "1110000", "铜陵", "TL"});
//        citys.add(new String[]{"1110400", "2", "1110000", "安庆", "AQ"});
//        citys.add(new String[]{"1110450", "2", "1110000", "黄山", "HS"});
//        citys.add(new String[]{"1110500", "2", "1110000", "滁州", "CZ"});
//        citys.add(new String[]{"1110550", "2", "1110000", "阜阳", "FY"});
//        citys.add(new String[]{"1110600", "2", "1110000", "宿州", "SZ"});
//        citys.add(new String[]{"1110650", "2", "1110000", "巢湖", "CH"});
//        citys.add(new String[]{"1110700", "2", "1110000", "六安", "LA"});
//        citys.add(new String[]{"1110750", "2", "1110000", "亳州", "BZ"});
//        citys.add(new String[]{"1110800", "2", "1110000", "池州", "CZ"});
//        citys.add(new String[]{"1110850", "2", "1110000", "宣城", "XC"});
//        citys.add(new String[]{"1120000", "1", "", "福建", "FJ"});
//        citys.add(new String[]{"1120050", "2", "1120000", "福州", "FZ"});
//        citys.add(new String[]{"1120100", "2", "1120000", "厦门", "SM"});
//        citys.add(new String[]{"1120150", "2", "1120000", "莆田", "PT"});
//        citys.add(new String[]{"1120200", "2", "1120000", "三明", "SM"});
//        citys.add(new String[]{"1120250", "2", "1120000", "泉州", "QZ"});
//        citys.add(new String[]{"1120300", "2", "1120000", "漳州", "ZZ"});
//        citys.add(new String[]{"1120350", "2", "1120000", "南平", "NP"});
//        citys.add(new String[]{"1120400", "2", "1120000", "龙岩", "LY"});
//        citys.add(new String[]{"1120450", "2", "1120000", "宁德", "ND"});
//        citys.add(new String[]{"1130000", "1", "", "江西", "JX"});
//        citys.add(new String[]{"1130050", "2", "1130000", "南昌", "NC"});
//        citys.add(new String[]{"1130100", "2", "1130000", "景德镇", "JDZ"});
//        citys.add(new String[]{"1130150", "2", "1130000", "萍乡", "PX"});
//        citys.add(new String[]{"1130200", "2", "1130000", "九江", "JJ"});
//        citys.add(new String[]{"1130250", "2", "1130000", "新余", "XY"});
//        citys.add(new String[]{"1130300", "2", "1130000", "鹰潭", "YT"});
//        citys.add(new String[]{"1130350", "2", "1130000", "赣州", "GZ"});
//        citys.add(new String[]{"1130400", "2", "1130000", "吉安", "JA"});
//        citys.add(new String[]{"1130450", "2", "1130000", "宜春", "YC"});
//        citys.add(new String[]{"1130500", "2", "1130000", "抚州", "FZ"});
//        citys.add(new String[]{"1130550", "2", "1130000", "上饶", "SR"});
//        citys.add(new String[]{"1140000", "1", "", "山东", "SD"});
//        citys.add(new String[]{"1140050", "2", "1140000", "济南", "JN"});
//        citys.add(new String[]{"1140100", "2", "1140000", "青岛", "QD"});
//        citys.add(new String[]{"1140150", "2", "1140000", "淄博", "ZB"});
//        citys.add(new String[]{"1140200", "2", "1140000", "枣庄", "ZZ"});
//        citys.add(new String[]{"1140250", "2", "1140000", "东营", "DY"});
//        citys.add(new String[]{"1140300", "2", "1140000", "烟台", "YT"});
//        citys.add(new String[]{"1140350", "2", "1140000", "潍坊", "WF"});
//        citys.add(new String[]{"1140400", "2", "1140000", "济宁", "JN"});
//        citys.add(new String[]{"1140450", "2", "1140000", "泰安", "TA"});
//        citys.add(new String[]{"1140500", "2", "1140000", "威海", "WH"});
//        citys.add(new String[]{"1140550", "2", "1140000", "日照", "RZ"});
//        citys.add(new String[]{"1140600", "2", "1140000", "莱芜", "LW"});
//        citys.add(new String[]{"1140650", "2", "1140000", "临沂", "LY"});
//        citys.add(new String[]{"1140700", "2", "1140000", "德州", "DZ"});
//        citys.add(new String[]{"1140750", "2", "1140000", "聊城", "LC"});
//        citys.add(new String[]{"1140800", "2", "1140000", "滨州", "BZ"});
//        citys.add(new String[]{"1140850", "2", "1140000", "菏泽", "HZ"});
//        citys.add(new String[]{"1150000", "1", "", "河南", "HN"});
//        citys.add(new String[]{"1150050", "2", "1150000", "郑州", "ZZ"});
//        citys.add(new String[]{"1150100", "2", "1150000", "开封", "KF"});
//        citys.add(new String[]{"1150150", "2", "1150000", "洛阳", "LY"});
//        citys.add(new String[]{"1150200", "2", "1150000", "平顶山", "PDS"});
//        citys.add(new String[]{"1150250", "2", "1150000", "安阳", "AY"});
//        citys.add(new String[]{"1150300", "2", "1150000", "鹤壁", "HB"});
//        citys.add(new String[]{"1150350", "2", "1150000", "新乡", "XX"});
//        citys.add(new String[]{"1150400", "2", "1150000", "焦作", "JZ"});
//        citys.add(new String[]{"1150450", "2", "1150000", "濮阳", "PY"});
//        citys.add(new String[]{"1150500", "2", "1150000", "许昌", "XC"});
//        citys.add(new String[]{"1150550", "2", "1150000", "漯河", "LH"});
//        citys.add(new String[]{"1150600", "2", "1150000", "三门峡", "SMX"});
//        citys.add(new String[]{"1150650", "2", "1150000", "南阳", "NY"});
//        citys.add(new String[]{"1150700", "2", "1150000", "商丘", "SQ"});
//        citys.add(new String[]{"1150750", "2", "1150000", "信阳", "XY"});
//        citys.add(new String[]{"1150800", "2", "1150000", "周口", "ZK"});
//        citys.add(new String[]{"1150850", "2", "1150000", "驻马店", "ZMD"});
//        citys.add(new String[]{"1150900", "2", "1150000", "济源", "JY"});
//        citys.add(new String[]{"1160000", "1", "", "湖北", "HB"});
//        citys.add(new String[]{"1160050", "2", "1160000", "武汉", "WH"});
//        citys.add(new String[]{"1160100", "2", "1160000", "黄石", "HS"});
//        citys.add(new String[]{"1160150", "2", "1160000", "十堰", "SY"});
//        citys.add(new String[]{"1160200", "2", "1160000", "宜昌", "YC"});
//        citys.add(new String[]{"1160250", "2", "1160000", "襄阳", "XY"});
//        citys.add(new String[]{"1160300", "2", "1160000", "鄂州", "EZ"});
//        citys.add(new String[]{"1160350", "2", "1160000", "荆门", "JM"});
//        citys.add(new String[]{"1160400", "2", "1160000", "孝感", "XG"});
//        citys.add(new String[]{"1160450", "2", "1160000", "荆州", "JZ"});
//        citys.add(new String[]{"1160500", "2", "1160000", "黄冈", "HG"});
//        citys.add(new String[]{"1160550", "2", "1160000", "咸宁", "XN"});
//        citys.add(new String[]{"1160600", "2", "1160000", "随州", "SZ"});
//        citys.add(new String[]{"1160650", "2", "1160000", "恩施", "ES"});
//        citys.add(new String[]{"1160700", "2", "1160000", "潜江", "QJ"});
//        citys.add(new String[]{"1160750", "2", "1160000", "仙桃", "XT"});
//        citys.add(new String[]{"1160800", "2", "1160000", "天门", "TM"});
//        citys.add(new String[]{"1160850", "2", "1160000", "神农架", "SNJ"});
//        citys.add(new String[]{"1170000", "1", "", "湖南", "HN"});
//        citys.add(new String[]{"1170050", "2", "1170000", "长沙", "ZS"});
//        citys.add(new String[]{"1170100", "2", "1170000", "株洲", "ZZ"});
//        citys.add(new String[]{"1170150", "2", "1170000", "湘潭", "XT"});
//        citys.add(new String[]{"1170200", "2", "1170000", "衡阳", "HY"});
//        citys.add(new String[]{"1170250", "2", "1170000", "邵阳", "SY"});
//        citys.add(new String[]{"1170300", "2", "1170000", "岳阳", "YY"});
//        citys.add(new String[]{"1170350", "2", "1170000", "常德", "CD"});
//        citys.add(new String[]{"1170400", "2", "1170000", "张家界", "ZJJ"});
//        citys.add(new String[]{"1170450", "2", "1170000", "益阳", "YY"});
//        citys.add(new String[]{"1170500", "2", "1170000", "郴州", "CZ"});
//        citys.add(new String[]{"1170550", "2", "1170000", "永州", "YZ"});
//        citys.add(new String[]{"1170600", "2", "1170000", "怀化", "HH"});
//        citys.add(new String[]{"1170650", "2", "1170000", "娄底", "LD"});
//        citys.add(new String[]{"1170700", "2", "1170000", "湘西", "XX"});
//        citys.add(new String[]{"1180000", "1", "", "广东", "GD"});
//        citys.add(new String[]{"1180050", "2", "1180000", "广州", "GZ"});
//        citys.add(new String[]{"1180100", "2", "1180000", "韶关", "SG"});
//        citys.add(new String[]{"1180150", "2", "1180000", "深圳", "SZ"});
//        citys.add(new String[]{"1180200", "2", "1180000", "珠海", "ZH"});
//        citys.add(new String[]{"1180250", "2", "1180000", "汕头", "ST"});
//        citys.add(new String[]{"1180300", "2", "1180000", "佛山", "FS"});
//        citys.add(new String[]{"1180350", "2", "1180000", "江门", "JM"});
//        citys.add(new String[]{"1180400", "2", "1180000", "湛江", "ZJ"});
//        citys.add(new String[]{"1180450", "2", "1180000", "茂名", "MM"});
//        citys.add(new String[]{"1180500", "2", "1180000", "肇庆", "ZQ"});
//        citys.add(new String[]{"1180550", "2", "1180000", "惠州", "HZ"});
//        citys.add(new String[]{"1180600", "2", "1180000", "梅州", "MZ"});
//        citys.add(new String[]{"1180650", "2", "1180000", "汕尾", "SW"});
//        citys.add(new String[]{"1180700", "2", "1180000", "河源", "HY"});
//        citys.add(new String[]{"1180750", "2", "1180000", "阳江", "YJ"});
//        citys.add(new String[]{"1180800", "2", "1180000", "清远", "QY"});
//        citys.add(new String[]{"1180850", "2", "1180000", "东莞", "DG"});
//        citys.add(new String[]{"1180900", "2", "1180000", "中山", "ZS"});
//        citys.add(new String[]{"1180950", "2", "1180000", "东沙群岛", "DSQD"});
//        citys.add(new String[]{"1181000", "2", "1180000", "潮州", "CZ"});
//        citys.add(new String[]{"1181050", "2", "1180000", "揭阳", "JY"});
//        citys.add(new String[]{"1181100", "2", "1180000", "云浮", "YF"});
//        citys.add(new String[]{"1190000", "1", "", "广西", "GX"});
//        citys.add(new String[]{"1190050", "2", "1190000", "南宁", "NN"});
//        citys.add(new String[]{"1190100", "2", "1190000", "柳州", "LZ"});
//        citys.add(new String[]{"1190150", "2", "1190000", "桂林", "GL"});
//        citys.add(new String[]{"1190200", "2", "1190000", "梧州", "WZ"});
//        citys.add(new String[]{"1190250", "2", "1190000", "北海", "BH"});
//        citys.add(new String[]{"1190300", "2", "1190000", "防城港", "FCG"});
//        citys.add(new String[]{"1190350", "2", "1190000", "钦州", "QZ"});
//        citys.add(new String[]{"1190400", "2", "1190000", "贵港", "GG"});
//        citys.add(new String[]{"1190450", "2", "1190000", "玉林", "YL"});
//        citys.add(new String[]{"1190500", "2", "1190000", "百色", "BS"});
//        citys.add(new String[]{"1190550", "2", "1190000", "贺州", "HZ"});
//        citys.add(new String[]{"1190600", "2", "1190000", "河池", "HC"});
//        citys.add(new String[]{"1190650", "2", "1190000", "来宾", "LB"});
//        citys.add(new String[]{"1190700", "2", "1190000", "崇左", "CZ"});
//        citys.add(new String[]{"1200000", "1", "", "海南", "HN"});
//        citys.add(new String[]{"1200050", "2", "1200000", "海口", "HK"});
//        citys.add(new String[]{"1200100", "2", "1200000", "三亚", "SY"});
//        citys.add(new String[]{"1200150", "2", "1200000", "临高县", "LGX"});
//        citys.add(new String[]{"1200200", "2", "1200000", "儋州", "DZ"});
//        citys.add(new String[]{"1200250", "2", "1200000", "屯昌县", "TCX"});
//        citys.add(new String[]{"1200300", "2", "1200000", "定安县", "DAX"});
//        citys.add(new String[]{"1200350", "2", "1200000", "澄迈县", "CMX"});
//        citys.add(new String[]{"1200400", "2", "1200000", "文昌", "WC"});
//        citys.add(new String[]{"1200450", "2", "1200000", "琼海", "QH"});
//        citys.add(new String[]{"1200500", "2", "1200000", "万宁", "WN"});
//        citys.add(new String[]{"1200550", "2", "1200000", "琼中县", "QZX"});
//        citys.add(new String[]{"1200600", "2", "1200000", "保亭县", "BTX"});
//        citys.add(new String[]{"1200650", "2", "1200000", "五指山", "WZS"});
//        citys.add(new String[]{"1200700", "2", "1200000", "白沙县", "BSX"});
//        citys.add(new String[]{"1200750", "2", "1200000", "东方", "DF"});
//        citys.add(new String[]{"1200800", "2", "1200000", "昌江县", "CJX"});
//        citys.add(new String[]{"1200850", "2", "1200000", "乐东县", "LDX"});
//        citys.add(new String[]{"1200900", "2", "1200000", "陵水县", "LSX"});
//        citys.add(new String[]{"1200950", "2", "1200000", "西沙群岛", "XSQD"});
//        citys.add(new String[]{"1201000", "2", "1200000", "南沙群岛", "NSQD"});
//        citys.add(new String[]{"1210000", "1", "", "重庆", "CQ"});
//        citys.add(new String[]{"1210050", "2", "1210000", "万州", "WZ"});
//        citys.add(new String[]{"1210100", "2", "1210000", "涪陵", "FL"});
//        citys.add(new String[]{"1210150", "2", "1210000", "渝中", "YZ"});
//        citys.add(new String[]{"1210200", "2", "1210000", "大渡口", "DDK"});
//        citys.add(new String[]{"1210250", "2", "1210000", "江北", "JB"});
//        citys.add(new String[]{"1210300", "2", "1210000", "沙坪坝", "SPB"});
//        citys.add(new String[]{"1210350", "2", "1210000", "九龙坡", "JLP"});
//        citys.add(new String[]{"1210400", "2", "1210000", "南岸", "NA"});
//        citys.add(new String[]{"1210450", "2", "1210000", "北碚", "BB"});
//        citys.add(new String[]{"1210500", "2", "1210000", "万盛", "WS"});
//        citys.add(new String[]{"1210550", "2", "1210000", "双桥", "SQ"});
//        citys.add(new String[]{"1210600", "2", "1210000", "渝北", "YB"});
//        citys.add(new String[]{"1210650", "2", "1210000", "巴南", "BN"});
//        citys.add(new String[]{"1210700", "2", "1210000", "黔江", "QJ"});
//        citys.add(new String[]{"1210750", "2", "1210000", "长寿", "ZS"});
//        citys.add(new String[]{"1210800", "2", "1210000", "江津", "JJ"});
//        citys.add(new String[]{"1210850", "2", "1210000", "合川", "HC"});
//        citys.add(new String[]{"1210900", "2", "1210000", "永川", "YC"});
//        citys.add(new String[]{"1210950", "2", "1210000", "南川", "NC"});
//        citys.add(new String[]{"1211000", "2", "1210000", "綦江", "QJ"});
//        citys.add(new String[]{"1211050", "2", "1210000", "潼南", "TN"});
//        citys.add(new String[]{"1211100", "2", "1210000", "铜梁", "TL"});
//        citys.add(new String[]{"1211150", "2", "1210000", "大足", "DZ"});
//        citys.add(new String[]{"1211200", "2", "1210000", "荣昌", "RC"});
//        citys.add(new String[]{"1211250", "2", "1210000", "璧山", "BS"});
//        citys.add(new String[]{"1211300", "2", "1210000", "梁平", "LP"});
//        citys.add(new String[]{"1211350", "2", "1210000", "城口", "CK"});
//        citys.add(new String[]{"1211400", "2", "1210000", "丰都", "FD"});
//        citys.add(new String[]{"1211450", "2", "1210000", "垫江", "DJ"});
//        citys.add(new String[]{"1211500", "2", "1210000", "武隆", "WL"});
//        citys.add(new String[]{"1211550", "2", "1210000", "忠县", "ZX"});
//        citys.add(new String[]{"1211600", "2", "1210000", "开县", "KX"});
//        citys.add(new String[]{"1211650", "2", "1210000", "云阳", "YY"});
//        citys.add(new String[]{"1211700", "2", "1210000", "奉节", "FJ"});
//        citys.add(new String[]{"1211750", "2", "1210000", "巫山", "WS"});
//        citys.add(new String[]{"1211800", "2", "1210000", "巫溪", "WX"});
//        citys.add(new String[]{"1211850", "2", "1210000", "石柱", "SZ"});
//        citys.add(new String[]{"1211900", "2", "1210000", "秀山", "XS"});
//        citys.add(new String[]{"1211950", "2", "1210000", "酉阳", "YY"});
//        citys.add(new String[]{"1212000", "2", "1210000", "彭水", "PS"});
//        citys.add(new String[]{"1220000", "1", "", "四川", "SC"});
//        citys.add(new String[]{"1220050", "2", "1220000", "成都", "CD"});
//        citys.add(new String[]{"1220100", "2", "1220000", "自贡", "ZG"});
//        citys.add(new String[]{"1220150", "2", "1220000", "攀枝花", "PZH"});
//        citys.add(new String[]{"1220200", "2", "1220000", "泸州", "LZ"});
//        citys.add(new String[]{"1220250", "2", "1220000", "德阳", "DY"});
//        citys.add(new String[]{"1220300", "2", "1220000", "绵阳", "MY"});
//        citys.add(new String[]{"1220350", "2", "1220000", "广元", "GY"});
//        citys.add(new String[]{"1220400", "2", "1220000", "遂宁", "SN"});
//        citys.add(new String[]{"1220450", "2", "1220000", "内江", "NJ"});
//        citys.add(new String[]{"1220500", "2", "1220000", "乐山", "LS"});
//        citys.add(new String[]{"1220550", "2", "1220000", "南充", "NC"});
//        citys.add(new String[]{"1220600", "2", "1220000", "眉山", "MS"});
//        citys.add(new String[]{"1220650", "2", "1220000", "宜宾", "YB"});
//        citys.add(new String[]{"1220700", "2", "1220000", "广安", "GA"});
//        citys.add(new String[]{"1220750", "2", "1220000", "达州", "DZ"});
//        citys.add(new String[]{"1220800", "2", "1220000", "雅安", "YA"});
//        citys.add(new String[]{"1220850", "2", "1220000", "巴中", "BZ"});
//        citys.add(new String[]{"1220900", "2", "1220000", "资阳", "ZY"});
//        citys.add(new String[]{"1220950", "2", "1220000", "阿坝", "AB"});
//        citys.add(new String[]{"1221000", "2", "1220000", "甘孜", "GZ"});
//        citys.add(new String[]{"1221050", "2", "1220000", "凉山", "LS"});
//        citys.add(new String[]{"1230000", "1", "", "贵州", "GZ"});
//        citys.add(new String[]{"1230050", "2", "1230000", "贵阳", "GY"});
//        citys.add(new String[]{"1230100", "2", "1230000", "六盘水", "LPS"});
//        citys.add(new String[]{"1230150", "2", "1230000", "遵义", "ZY"});
//        citys.add(new String[]{"1230200", "2", "1230000", "安顺", "AS"});
//        citys.add(new String[]{"1230250", "2", "1230000", "铜仁", "TR"});
//        citys.add(new String[]{"1230300", "2", "1230000", "黔西", "QX"});
//        citys.add(new String[]{"1230350", "2", "1230000", "毕节", "BJ"});
//        citys.add(new String[]{"1230400", "2", "1230000", "黔东", "QD"});
//        citys.add(new String[]{"1230450", "2", "1230000", "黔南", "QN"});
//        citys.add(new String[]{"1240000", "1", "", "云南", "YN"});
//        citys.add(new String[]{"1240050", "2", "1240000", "昆明", "KM"});
//        citys.add(new String[]{"1240100", "2", "1240000", "曲靖", "QJ"});
//        citys.add(new String[]{"1240150", "2", "1240000", "玉溪", "YX"});
//        citys.add(new String[]{"1240200", "2", "1240000", "保山", "BS"});
//        citys.add(new String[]{"1240250", "2", "1240000", "昭通", "ZT"});
//        citys.add(new String[]{"1240300", "2", "1240000", "丽江", "LJ"});
//        citys.add(new String[]{"1240350", "2", "1240000", "普洱", "PE"});
//        citys.add(new String[]{"1240400", "2", "1240000", "临沧", "LC"});
//        citys.add(new String[]{"1240450", "2", "1240000", "楚雄", "CX"});
//        citys.add(new String[]{"1240500", "2", "1240000", "红河", "HH"});
//        citys.add(new String[]{"1240550", "2", "1240000", "文山", "WS"});
//        citys.add(new String[]{"1240600", "2", "1240000", "西双版纳", "XSBN"});
//        citys.add(new String[]{"1240650", "2", "1240000", "大理", "DL"});
//        citys.add(new String[]{"1240700", "2", "1240000", "德宏", "DH"});
//        citys.add(new String[]{"1240750", "2", "1240000", "怒江", "NJ"});
//        citys.add(new String[]{"1240800", "2", "1240000", "迪庆", "DQ"});
//        citys.add(new String[]{"1250000", "1", "", "西藏", "XZ"});
//        citys.add(new String[]{"1250050", "2", "1250000", "拉萨", "LS"});
//        citys.add(new String[]{"1250100", "2", "1250000", "昌都", "CD"});
//        citys.add(new String[]{"1250150", "2", "1250000", "山南", "SN"});
//        citys.add(new String[]{"1250200", "2", "1250000", "日喀则", "RKZ"});
//        citys.add(new String[]{"1250250", "2", "1250000", "那曲", "NQ"});
//        citys.add(new String[]{"1250300", "2", "1250000", "阿里", "AL"});
//        citys.add(new String[]{"1250350", "2", "1250000", "林芝", "LZ"});
//        citys.add(new String[]{"1260000", "1", "", "陕西", "SX"});
//        citys.add(new String[]{"1260050", "2", "1260000", "西安", "XA"});
//        citys.add(new String[]{"1260100", "2", "1260000", "铜川", "TC"});
//        citys.add(new String[]{"1260150", "2", "1260000", "宝鸡", "BJ"});
//        citys.add(new String[]{"1260200", "2", "1260000", "咸阳", "XY"});
//        citys.add(new String[]{"1260250", "2", "1260000", "渭南", "WN"});
//        citys.add(new String[]{"1260300", "2", "1260000", "延安", "YA"});
//        citys.add(new String[]{"1260350", "2", "1260000", "汉中", "HZ"});
//        citys.add(new String[]{"1260400", "2", "1260000", "榆林", "YL"});
//        citys.add(new String[]{"1260450", "2", "1260000", "安康", "AK"});
//        citys.add(new String[]{"1260500", "2", "1260000", "商洛", "SL"});
//        citys.add(new String[]{"1270000", "1", "", "甘肃", "GS"});
//        citys.add(new String[]{"1270050", "2", "1270000", "兰州", "LZ"});
//        citys.add(new String[]{"1270100", "2", "1270000", "嘉峪关", "JYG"});
//        citys.add(new String[]{"1270150", "2", "1270000", "金昌", "JC"});
//        citys.add(new String[]{"1270200", "2", "1270000", "白银", "BY"});
//        citys.add(new String[]{"1270250", "2", "1270000", "天水", "TS"});
//        citys.add(new String[]{"1270300", "2", "1270000", "武威", "WW"});
//        citys.add(new String[]{"1270350", "2", "1270000", "张掖", "ZY"});
//        citys.add(new String[]{"1270400", "2", "1270000", "平凉", "PL"});
//        citys.add(new String[]{"1270450", "2", "1270000", "酒泉", "JQ"});
//        citys.add(new String[]{"1270500", "2", "1270000", "庆阳", "QY"});
//        citys.add(new String[]{"1270550", "2", "1270000", "定西", "DX"});
//        citys.add(new String[]{"1270600", "2", "1270000", "陇南", "LN"});
//        citys.add(new String[]{"1270650", "2", "1270000", "临夏", "LX"});
//        citys.add(new String[]{"1270700", "2", "1270000", "甘南", "GN"});
//        citys.add(new String[]{"1280000", "1", "", "青海", "QH"});
//        citys.add(new String[]{"1280050", "2", "1280000", "西宁", "XN"});
//        citys.add(new String[]{"1280100", "2", "1280000", "海东", "HD"});
//        citys.add(new String[]{"1280150", "2", "1280000", "海北", "HB"});
//        citys.add(new String[]{"1280200", "2", "1280000", "黄南", "HN"});
//        citys.add(new String[]{"1280250", "2", "1280000", "海南", "HN"});
//        citys.add(new String[]{"1280300", "2", "1280000", "果洛", "GL"});
//        citys.add(new String[]{"1280350", "2", "1280000", "玉树", "YS"});
//        citys.add(new String[]{"1280400", "2", "1280000", "海西", "HX"});
//        citys.add(new String[]{"1290000", "1", "", "宁夏", "NX"});
//        citys.add(new String[]{"1290050", "2", "1290000", "银川", "YC"});
//        citys.add(new String[]{"1290100", "2", "1290000", "石嘴山", "SZS"});
//        citys.add(new String[]{"1290150", "2", "1290000", "吴忠", "WZ"});
//        citys.add(new String[]{"1290200", "2", "1290000", "固原", "GY"});
//        citys.add(new String[]{"1290250", "2", "1290000", "中卫", "ZW"});
//        citys.add(new String[]{"1300000", "1", "", "新疆", "XJ"});
//        citys.add(new String[]{"1300050", "2", "1300000", "乌鲁木齐", "WLMQ"});
//        citys.add(new String[]{"1300100", "2", "1300000", "克拉玛依", "KLMY"});
//        citys.add(new String[]{"1300150", "2", "1300000", "吐鲁番", "TLF"});
//        citys.add(new String[]{"1300200", "2", "1300000", "哈密", "HM"});
//        citys.add(new String[]{"1300250", "2", "1300000", "昌吉", "CJ"});
//        citys.add(new String[]{"1300300", "2", "1300000", "博尔塔拉", "BETL"});
//        citys.add(new String[]{"1300350", "2", "1300000", "巴音郭楞", "BYGL"});
//        citys.add(new String[]{"1300400", "2", "1300000", "阿克苏", "AKS"});
//        citys.add(new String[]{"1300450", "2", "1300000", "克孜勒苏", "KZLS"});
//        citys.add(new String[]{"1300500", "2", "1300000", "喀什", "KS"});
//        citys.add(new String[]{"1300550", "2", "1300000", "和田", "HT"});
//        citys.add(new String[]{"1300600", "2", "1300000", "伊犁", "YL"});
//        citys.add(new String[]{"1300650", "2", "1300000", "塔城", "TC"});
//        citys.add(new String[]{"1300700", "2", "1300000", "阿勒泰", "ALT"});
//        citys.add(new String[]{"1300750", "2", "1300000", "石河子", "SHZ"});
//        citys.add(new String[]{"1300800", "2", "1300000", "阿拉尔", "ALE"});
//        citys.add(new String[]{"1300850", "2", "1300000", "图木舒克", "TMSK"});
//        citys.add(new String[]{"1300900", "2", "1300000", "五家渠", "WJQ"});
//
//        Collections.sort(citys, new Comparator<String[]>() {
//
//            @Override
//            public int compare(String[] lhs, String[] rhs) {
//                return lhs[4].compareTo(rhs[4]);
//            }
//        });
//    }
//}
