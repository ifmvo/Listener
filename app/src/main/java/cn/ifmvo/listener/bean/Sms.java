package cn.ifmvo.listener.bean;

import com.ifmvo.matthew.base.bean.BaseBean;

/**
 * Created by 陈序员 on 2017/5/23.
 * Email: Matthew_Chen_1994@163.com
 * Blog: https://blog.ifmvo.cn
 */

public class Sms extends BaseBean {

    public String content;

    public String phone;

    public String sendDate;

    public Sms(String content, String phone, String sendDate) {
        this.content = content;
        this.sendDate = sendDate;
        this.phone = phone;
    }
}
