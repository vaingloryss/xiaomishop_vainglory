package com.vainglory.domain;

import java.util.List;

/*
 * wgy 2019/9/11 11:24
 * 佛祖保佑，永无BUG!
 */
public class PageBean<T> {
    private int pageNum;
    private int pageSize;
    private long totalSize;
    private int totalPage;
    private List<T> data;
    private int startPage;
    private int endPage;

    public PageBean(int pageNum, int pageSize, long totalSize, List<T> data) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
        this.data = data;
        //计算总页数
        this.totalPage= (int) (totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1);
        //计算导航的开始页和结束页
        //1 正常的情况   当前页面 -4  +5
        this.startPage=pageNum-4;
        this.endPage=pageNum+5;
        //2 当前页码小于5
        if(pageNum<5){
            this.startPage=1;
            this.endPage=10;
        }
        //3当前页大于 总页数-5
        if(pageNum>(totalPage-5)){
            this.startPage=totalPage-9;
            this.endPage=totalPage;
        }
        //4总页数小于10
        if(totalPage<10){
            this.startPage=1;
            this.endPage=totalPage;
        }
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
}
