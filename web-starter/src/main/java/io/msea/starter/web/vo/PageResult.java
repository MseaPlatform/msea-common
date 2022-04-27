package io.msea.starter.web.vo;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author msea-admin
 * @create 2022-01-25 15:30
 */
public class PageResult<T> {
    private int pageNum;
    private int pageSize;
    private long count;
    private int pages;
    private List<T> list;

    public static <T> PageResult<T> create(int pageNum, int pageSize, long count, int pages, List<T> dataList) {
        if (CollectionUtils.isEmpty((Collection)dataList)) {
            dataList = new ArrayList();
        }

        PageResult<T> pageResult = new PageResult(pageNum, pageSize, count, pages, (List)dataList);
        return pageResult;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public long getCount() {
        return this.count;
    }

    public int getPages() {
        return (this.count % this.getPageSize() > 0 ? 1 : 0) + (int)(this.count / this.getPageSize()) ;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNum(final int pageNum) {
        this.pageNum = pageNum;
    }

    public void setCount(final long count) {
        this.count = count;
    }

    public void setPages(final int pages) {
        this.pages = pages;
    }

    public void setList(final List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", count=" + count +
                ", pages=" + pages +
                ", list=" + list +
                '}';
    }

    public PageResult(final int pageNum, final int pageSize, final long count, final int pages, final List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.count = count;
        this.pages = pages;
        this.list = list;
    }

    public PageResult() {
    }
}
