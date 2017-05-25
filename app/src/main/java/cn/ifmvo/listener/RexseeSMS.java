package cn.ifmvo.listener;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陈序员 on 2017/5/22.
 * Email: Matthew_Chen_1994@163.com
 * Blog: https://blog.ifmvo.cn
 */

public class RexseeSMS {

    public static final String CONTENT_URI_SMS = "content://sms"; // 短信
    public static final String CONTENT_URI_SMS_INBOX = "content://sms/inbox";// 收件箱
    public static final String CONTENT_URI_SMS_SENT = "content://sms/sent"; // 发送
    public static final String CONTENT_URI_SMS_CONVERSATIONS = "content://sms/conversations";

    public RexseeSMS(Context mContext) {
        this.mContext = mContext;
        // TODO Auto-generated constructor stub
    }

    public static String[] SMS_COLUMNS = new String[] { "_id", // 0
            "thread_id", // 1
            "address", // 2
            "person", // 3
            "date", // 4
            "body", // 5
            "read", // 6; 0:not read 1:read; default is 0
            "type", // 7; 0:all 1:inBox 2:sent 3:draft 4:outBox 5:failed
            // 6:queued
            "service_center" // 8
    };
    public static String[] THREAD_COLUMNS = new String[] { "thread_id",
            "msg_count", "snippet" };

    private Context mContext;

    public String getContentUris() {
        String rtn = "{";
        rtn += "\"sms\":\"" + CONTENT_URI_SMS + "\"";
        rtn += ",\"inbox\":\"" + CONTENT_URI_SMS_INBOX + "\"";
        rtn += ",\"sent\":\"" + CONTENT_URI_SMS_SENT + "\"";
        rtn += ",\"conversations\":\"" + CONTENT_URI_SMS_CONVERSATIONS + "\"";
        rtn += "}";
        return rtn;
    }

    public String get(int number) {
        return getData(null, number);
    }

    public String getUnread(int number) {
        return getData("type=1 AND read=0", number);
    }

    public String getRead(int number) {
        return getData("type=1 AND read=1", number);
    }

    public String getInbox(int number) {
        return getData("type=1", number);
    }

    public String getSent(int number) {
        return getData("type=2", number);
    }

    public String getByThread(int thread) {
        return getData("thread_id=" + thread, 0);
    }

    public String getData(String selection, int number) {
        Cursor cursor = null;
        ContentResolver contentResolver = mContext.getContentResolver();
        try {
            if (number > 0) {
                cursor = contentResolver.query(Uri.parse(CONTENT_URI_SMS),
                        SMS_COLUMNS, selection, null, "date desc limit "
                                + number);
            } else {
                cursor = contentResolver.query(Uri.parse(CONTENT_URI_SMS),
                        SMS_COLUMNS, selection, null, "date desc");
            }
            if (cursor == null || cursor.getCount() == 0)
                return "[]";
            String rtn = "";
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                if (i > 0)
                    rtn += ",";
                rtn += "{";
                rtn += "\"_id\":" + cursor.getString(0);
                rtn += ",\"thread_id\":" + cursor.getString(1);
                rtn += ",\"address\":\"" + cursor.getString(2) + "\"";
                rtn += ",\"person\":\""
                        + ((cursor.getString(3) == null) ? "" : cursor
                        .getString(3)) + "\"";
                rtn += ",\"date\":" + cursor.getString(4);
                rtn += ",\"body\":\"" + cursor.getString(5) + "\"";
                rtn += ",\"read\":"
                        + ((cursor.getInt(6) == 1) ? "true" : "false");
                rtn += ",\"type\":" + cursor.getString(7);
                rtn += ",\"service_center\":" + cursor.getString(8);
                rtn += "}";
            }
            return "[" + rtn + "]";
        } catch (Exception e) {
            return "[]";
        }
    }

    public List<SMSBean> getThreads(int number) {
        Cursor cursor = null;
        ContentResolver contentResolver = mContext.getContentResolver();
        List<SMSBean> list = new ArrayList<SMSBean>();
        try {
            if (number > 0) {
                cursor = contentResolver.query(
                        Uri.parse(CONTENT_URI_SMS_CONVERSATIONS),
                        THREAD_COLUMNS, null, null, "thread_id desc limit "
                                + number);
            } else {
                cursor = contentResolver.query(
                        Uri.parse(CONTENT_URI_SMS_CONVERSATIONS),
                        THREAD_COLUMNS, null, null, "date desc");
            }
            if (cursor == null || cursor.getCount() == 0)
                return list;
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                SMSBean mmt = new SMSBean(cursor.getString(0),
                        cursor.getString(1), cursor.getString(2));
                list.add(mmt);
            }
            return list;
        } catch (Exception e) {
            return list;
        }
    }

    public List<SMSBean> getThreadsNum(List<SMSBean> ll) {

        Cursor cursor = null;
        ContentResolver contentResolver = mContext.getContentResolver();
        List<SMSBean> list = new ArrayList<SMSBean>();
        // try {
        for (SMSBean mmt : ll) {
            cursor = contentResolver.query(Uri.parse(CONTENT_URI_SMS),
                    SMS_COLUMNS, "thread_id = " + mmt.getThread_id(), null,
                    null);
            if (cursor == null || cursor.getCount() == 0)
                return list;
            cursor.moveToFirst();
            mmt.setAddress(cursor.getString(2));
            mmt.setDate(cursor.getLong(4));
            mmt.setRead(cursor.getString(6));
            list.add(mmt);
        }

        return list;
        // } catch (Exception e) {
        // Log.e("getThreadsNum", "getThreadsNum-------------");
        // return list;
        // }
    }
}
