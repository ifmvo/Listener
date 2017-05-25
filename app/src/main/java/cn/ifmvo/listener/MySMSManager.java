package cn.ifmvo.listener;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.tbruyelle.rxpermissions.RxPermissions;

import rx.functions.Action1;

/**
 * Created by 陈序员 on 2017/5/22.
 * Email: Matthew_Chen_1994@163.com
 * Blog: https://blog.ifmvo.cn
 */

public class MySMSManager extends Activity {

    private TextView textview;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_main);
        textview = (TextView) findViewById(R.id.txt);

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.READ_SMS)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean){
                            readShortMessage();
                        }else{

                        }
                    }
                });
    }

    public void readShortMessage() {
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(Uri.parse("content://sms/inbox"), null, null, null, null);
        String msg = "";
        while(cursor.moveToNext()) {
            int phoneColumn = cursor.getColumnIndex("address");
            int smsColumn = cursor.getColumnIndex("body");
            msg += cursor.getString(phoneColumn) + ":" + cursor.getString(smsColumn) + "\n";
        }
        textview.setText(msg);
        Log.e("msg", msg);
    }
}
