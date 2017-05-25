package cn.ifmvo.listener.ui;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.ifmvo.matthew.base.activity.ExBaseTopBarActivity;
import com.ifmvo.matthew.utils.DateUtils;

import java.util.ArrayList;

import cn.ifmvo.listener.R;
import cn.ifmvo.listener.bean.Sms;

/**
 * Created by 陈序员 on 2017/5/25.
 * Email: Matthew_Chen_1994@163.com
 * Blog: https://blog.ifmvo.cn
 */

public class MainActivity extends ExBaseTopBarActivity{

    Button btnList, btnTongJi;

    String phone  = "";

    ArrayList<Sms> listAll = new ArrayList<>();

    @Override
    public void onCreatedContentView() {
        setMainContentView(R.layout.activity_main);
        setTitle("报警统计");
//        btnTopL.setVisibility(View.GONE);
    }

    @Override
    public void onFindView() {
        btnList = (Button) findViewById(R.id.btnList);
        btnTongJi = (Button) findViewById(R.id.btnTongJi);
    }

    @Override
    public void onPostLoad() {

        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");

        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                listAll = (ArrayList<Sms>) msg.obj;

                closeLoading();
            }
        };

        showLoading(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                ArrayList<Sms> list = getAllSMS();

                Message msg = new Message();
                msg.obj = list;
                handler.sendMessageDelayed(msg, 500);
            }
        }).start();


        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SmsListActivity.class);
                intent.putExtra("smsList", listAll);
                startActivity(intent);
            }
        });

        btnTongJi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TongJiActivity.class);
                intent.putExtra("smsList", listAll);
                startActivity(intent);
            }
        });
    }



    public ArrayList<Sms> getAllSMS(){
        ArrayList<Sms> list = new ArrayList<>();
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(Uri.parse("content://sms/inbox"), null, null, null, null);

        while(cursor.moveToNext()) {
            int phoneColumn = cursor.getColumnIndex("address");
            String phoneString = cursor.getString(phoneColumn);
            int smsColumn = cursor.getColumnIndex("body");
            String smsString = cursor.getString(smsColumn);
            int dateLong = cursor.getColumnIndex("date");
            long dateString = cursor.getLong(dateLong);


            if (phone != null && phone.trim().equals(phoneString)
                    && (smsString.equals("地点1报警，请注意")
                    ||  smsString.equals("地点2报警，请注意")
                    ||  smsString.equals("地点3报警，请注意")
                    ||  smsString.equals("紧急通知，请注意"))) {
                list.add(new Sms(smsString, phoneString, DateUtils.longToString(dateString, "yyyy-MM-dd")));
            }
        }

        return list;
    }
}
