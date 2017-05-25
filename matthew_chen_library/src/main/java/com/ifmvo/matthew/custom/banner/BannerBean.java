package com.ifmvo.matthew.custom.banner;


import com.ifmvo.matthew.base.bean.BaseBean;


/**
 * Created by ZongfaHe on 16/3/28.
 */
public class BannerBean extends BaseBean {
//    public void pushToNext(Context context) {
//        if (type == 0) {
//
//        } else if (type == 1) {
//            if (!StringUtil.isEmpty(view)) {
//                if (view.startsWith("homealbum?album_id=")) {
//                    UIHelper.pushActPublicAlbumMain((BaseActivity) context, Long.parseLong(view.split("=")[1]), 0);
//                } else if (view.startsWith("hometag?tag_id=")) {
//                    DiscountHotTagBean discountHotTagBean = new DiscountHotTagBean();
//                    discountHotTagBean.setTag_id(Long.parseLong(view.split("=")[1]));
//                    discountHotTagBean.setTag_name("");
//                    discountHotTagBean.setBackground("");
//                    UIHelper.pushActTagHome((BaseActivity) context, discountHotTagBean, 0);
//                } else if (view.startsWith("memory?memory_id=")) {
//                    UIHelper.pushActMemoryDetail((BaseActivity) context, 0, Long.parseLong(view.split("=")[1]), 0);
//                }
//            }
//
//        } else if (type == 2) {
//            if (link != null) {
//                String url = "";
//                if (!link.contains("http://")) {
//                    url = "http://" + link;
//                }else{
//                    url=link;
//                }
//
//                UIHelper.pushActExBaseWVBar((BaseActivity) context, url,0);
//            } else
//                FQL.e("link=null");
//        }
//    }

    /**
     * type：
     * 0 不操作
     * 1 APP内视图转跳
     * 2 网页转跳
     * <p>
     * <p>
     * APP内视图转跳
     * 字段 view：
     * homealbum?album_id=322  //代表转跳到相册主页
     * hometag?tag_id=2        //代表转跳到标签主页
     * memory?memory_id=322    //代表是寻找记忆
     * <p>
     * 超级链接转跳
     * 字段 link：
     */
    public int imgRes;
    public String img;
    public String link;
    public String name;
    public int sort;
    public int state;
    public int type;
    public String view;

    public BannerBean(String img) {
        this.img = img;
    }

    public BannerBean(int imgRes){
        this.imgRes = imgRes;
    }

}
