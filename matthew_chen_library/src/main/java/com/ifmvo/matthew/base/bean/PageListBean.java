package com.ifmvo.matthew.base.bean;


import java.util.List;

/**
 * Created by ZongfaHe on 16/3/27.
 */
public class PageListBean<T extends BaseBean> extends BaseBean {

    private PageBean pageInfo;
    private List<T> list;

    public PageBean getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageBean pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageListBean{" +
                "pageInfo=" + pageInfo +
                ", list=" + list +
                '}';
    }
}
