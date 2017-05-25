package com.ifmvo.matthew.base.parser;


import com.ifmvo.matthew.base.bean.BaseBean;

/**
 * Created by ZongfaHe on 16/3/16.
 */
public abstract class BaseParser<T> extends BaseBean implements Parser<T> {
//    private final String tag = getClass().getSimpleName();
//    private String requestUrl = "";
//
//    public String getRequestUrl() {
//        return requestUrl;
//    }
//
//    public BaseParser setRequestUrl(String requestUrl) {
//        this.requestUrl = requestUrl;
//        return this;
//    }
    //    public void parser(String str) {
//        if (!StringUtil.isEmpty(str)) {
//            int status = JsonExplain.getIntValue(str, JsonHelper.KEY_state);
//            Logger.d(tag, "---status----" + status);
//            String info = JsonExplain.getStringValue(str, JsonHelper.KEY_msg);
//            Logger.d(tag, "---info----" + info);
//            String data = JsonExplain.getStringValue(str, JsonHelper.KEY_data);
//            Logger.d(tag, "---data----" + data);
//            parser(data);
//        } else {
//            Logger.d(tag, "---result----null");
//        }
//    }

//    public abstract T parserData(String str);


//    @Override
//    public T parseArray(String str) {
//        return null;
//    }
}
