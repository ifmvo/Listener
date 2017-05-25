package cn.ifmvo.listener.ui;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ifmvo.matthew.base.activity.ExBaseTopBarActivity;
import com.ifmvo.matthew.utils.SharedPreference.PreferBaseUtil;
import com.ifmvo.matthew.utils.print.Toaster;
import com.tbruyelle.rxpermissions.RxPermissions;

import cn.ifmvo.listener.R;
import cn.ifmvo.listener.utils.PreferUtil;
import rx.functions.Action1;


/**
 * Created by 陈序员 on 2017/5/23.
 * Email: Matthew_Chen_1994@163.com
 * Blog: https://blog.ifmvo.cn
 */

public class LoginActivity extends ExBaseTopBarActivity {

    EditText etPhone;
    Button btnConfirm;

    @Override
    public void onCreatedContentView() {
        setMainContentView(R.layout.activity_login);
        setTitle("填写手机号");
        btnTopL.setVisibility(View.GONE);
//        layTopBar.setVisibility(View.GONE);
    }

    @Override
    public void onFindView() {

        PreferBaseUtil.init(getContext(), "cn_ifmvo_listener_SP");

        etPhone = (EditText) findViewById(R.id.etPhone);
//        etPhone.setText("17161783104");
        btnConfirm = (Button) findViewById(R.id.btnAction);

        String phone = PreferUtil.getInstance().getPhone();
        if (phone != null){
            etPhone.setText(phone);
        }
    }

    @Override
    public void onPostLoad() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phone = etPhone.getText().toString();

                if (phone == null){
                    Toaster.showLong(getContext(), "手机号不能为空！");
                    return ;
                }

                PreferUtil.getInstance().savePhone(phone);

                RxPermissions rxPermissions = new RxPermissions(getContext());
                rxPermissions
                        .request(Manifest.permission.READ_SMS)
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean aBoolean) {
                                if (aBoolean){
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("phone", phone);
                                    startActivity(intent);
                                }else{
                                    Toaster.showLong(getContext(), "一定要给我读短信的权限哦！");
                                }
                            }
                        });


            }
        });
    }
}
