package cn.ifmvo.listener.utils;

import com.ifmvo.matthew.utils.SharedPreference.PreferBaseUtil;

/**
 * Created by Matthew_Chen on 16/9/19.
 */
public class PreferUtil extends PreferBaseUtil {

    static PreferUtil preferUtils;

    public static PreferUtil getInstance() {
        if (preferUtils == null) {
            preferUtils = new PreferUtil();
        }
        return preferUtils;
    }


    public void savePhone(String phone){
        putString("phone", phone);
    }

    public String getPhone(){
        return getString("phone", "");
    }

//    /**
//     * 保存用户个人信息
//     * @param memberBean
//     */
//    public void saveMember(MemberBean memberBean){
//        String str = JsonExplain.toJson(memberBean);
//        putString("saveMember", str);
//    }
//
//    /**
//     * 查询用户个人信息
//     * @return
//     */
//    public MemberBean getMember(){
//        String str = getString("saveMember", "");
//        return JsonExplain.explainJson(str, MemberBean.class);
//    }
//
//    public void clearMember(){
//        remove("saveMember");
//    }
}
