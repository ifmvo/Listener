package cn.ifmvo.listener;

/**
 * Created by 陈序员 on 2017/5/22.
 * Email: Matthew_Chen_1994@163.com
 * Blog: https://blog.ifmvo.cn
 */

public class SMSBean {

    private String thread_id; // 线程id
    private String msg_count; // 消息个数
    private String msg_snippet; // 消息片段
    private String address; // 地址
    private Long date; // 日期
    private String read; // 已读


    public SMSBean(String threadId, String msgCount, String msgSnippet) {
        thread_id = threadId;
        msg_count = msgCount;
        msg_snippet = msgSnippet;
    }

    public String getThread_id() {
        return thread_id;
    }

    public void setThread_id(String thread_id) {
        this.thread_id = thread_id;
    }

    public String getMsg_count() {
        return msg_count;
    }

    public void setMsg_count(String msg_count) {
        this.msg_count = msg_count;
    }

    public String getMsg_snippet() {
        return msg_snippet;
    }

    public void setMsg_snippet(String msg_snippet) {
        this.msg_snippet = msg_snippet;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }
}