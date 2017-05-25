package cn.ifmvo.listener.ui;

import android.content.Intent;
import android.widget.TextView;

import com.ifmvo.matthew.base.activity.ExBaseTopBarActivity;

import java.util.ArrayList;

import cn.ifmvo.listener.R;
import cn.ifmvo.listener.bean.Sms;

/**
 * Created by 陈序员 on 2017/5/25.
 * Email: Matthew_Chen_1994@163.com
 * Blog: https://blog.ifmvo.cn
 */

public class TongJiActivity extends ExBaseTopBarActivity {

    ArrayList<Sms> listAll = new ArrayList<>();
    ArrayList<Sms> list1 = new ArrayList<>();
    ArrayList<Sms> list2 = new ArrayList<>();
    ArrayList<Sms> list3 = new ArrayList<>();
    ArrayList<Sms> listJ = new ArrayList<>();


    TextView tv1, tv2, tv3, tvJ, total;


    @Override
    public void onCreatedContentView() {
        setMainContentView(R.layout.activity_tong_ji);
        setTitle("统计");
    }

    @Override
    public void onFindView() {
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tvJ = (TextView) findViewById(R.id.tvJ);
        total = (TextView) findViewById(R.id.total);
    }

    public void refresh(){

    }

    @Override
    public void onPostLoad() {

        Intent intent = getIntent();
        listAll = (ArrayList<Sms>) intent.getSerializableExtra("smsList");

        for (Sms sms : listAll) {
            switch (sms.content){
                case "地点1报警，请注意":
                    list1.add(sms);
                    break;
                case "地点2报警，请注意":
                    list2.add(sms);
                    break;
                case "地点3报警，请注意":
                    list3.add(sms);
                    break;
                case "紧急通知，请注意":
                    listJ.add(sms);
                    break;
                default:
            }
        }

        tv1.setText(String.valueOf(list1.size()));
        tv2.setText(String.valueOf(list2.size()));
        tv3.setText(String.valueOf(list3.size()));
        tvJ.setText(String.valueOf(listJ.size()));
        total.setText(String.valueOf(listAll.size()));

    }
}
