package com.fzu.edu.utils;

import java.util.List;

/**
 * Created by panzx on 2017/12/22.
 */
public class Page {
    private int pageNo;
    private int pageSize;
    private int totalPage;
    private int totalCount;
    private List data;

    public Page() {
    }

    public Page(int pageNo, int pageSize, List data) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.data = data;
        this.totalCount = data.size();
        if(pageSize==0){
            return;
        }
        int temp = this.totalCount%pageSize;
        if(temp==0){
            this.totalPage=this.totalCount/pageSize;
        }
        else{
            this.totalPage=this.totalCount/pageSize+1;
        }
        if(this.totalCount>pageSize){
            int start=(pageNo-1)*pageSize;
            int end=start+pageSize;
            if(end>this.totalCount){
                end=this.totalCount;
            }
            this.data=data.subList(start,end);
        }
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
