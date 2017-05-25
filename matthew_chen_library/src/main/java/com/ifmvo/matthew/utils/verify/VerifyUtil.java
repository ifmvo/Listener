package com.ifmvo.matthew.utils.verify;

import com.ifmvo.matthew.utils.StringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/11/14.
 */
public class VerifyUtil {

    /**
     * 验证手机号码是否合法
     * @param phone
     * @return
     */
    public static String checkPhone(String phone) {
        if (StringUtil.isEmpty(phone))
            return "请填写手机号";
        if (phone.length() != 11)
            return "手机号不正确";
        return null;
    }

    /**
     * 判断邮编
     */
    public static boolean isZipNO(String zipString){
        String str = "^[1-9][0-9]{5}$";
        return Pattern.compile(str).matcher(zipString).matches();
    }


    /**
     * 判断邮箱是否合法
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        if (null == email || "".equals(email)) return false;
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }


//    class Error {
//        public boolean isOK;
//        public String errorMsg;
//    }
}
