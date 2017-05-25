package com.ifmvo.matthew.base.bean;


/**
 * Created by ZongfaHe on 16/3/23.
 */
public class PageBean extends BaseBean {
    private int maxpage;
    private int page;
    private int perPageSize;
    private long total;

    public int getMaxpage() {
        return maxpage;
    }

    public void setMaxpage(int maxpage) {
        this.maxpage = maxpage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPageSize() {
        return perPageSize;
    }

    public void setPerPageSize(int perPageSize) {
        this.perPageSize = perPageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
