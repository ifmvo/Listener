package com.ifmvo.matthew.base.parser;

import com.ifmvo.matthew.base.bean.BaseBean;
import com.ifmvo.matthew.base.bean.PageListBean;
import com.ifmvo.matthew.utils.JsonExplain;
import com.ifmvo.matthew.utils.StringUtil;

import java.util.List;

/**
 * Created by ZongfaHe on 16/3/27.
 */
public class PageListParser<T extends BaseBean> extends BaseParser<PageListBean> {
    String listItemName;
    T[] t;

    public PageListParser(T[] t, String listItemName) {
        this.listItemName = listItemName;
        this.t = t;
    }

    @Override
    public PageListBean parse(String str) {

//        String pageInfo = JsonExplain.getStringValue(str, "pageinfo");
//        Logger.e("str:" + str);
        String list = JsonExplain.getStringValue(str, listItemName);
//        Logger.e("list:" + list);

        PageListBean<T> pageListBean = new PageListBean<>();
//        if (!StringUtil.isEmpty(pageInfo))
//            pageListBean.setPageInfo((PageBean) JsonExplain.explainJson(pageInfo, PageBean.class));
        if (!StringUtil.isEmpty(list)) {
            List<T> tList =  JsonExplain.explainListJson(list, t.getClass());
//            Logger.e("tList:" + tList);
            pageListBean.setList(tList);
        }
//        if (!StringUtil.isEmpty(getRequestUrl()))
        return pageListBean;
    }
}
