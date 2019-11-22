package com.kisin.gen.common.pagination;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-11-06 10:39
 * @Description:
 */
public class Page<T> implements IPage<T> {

    private static final long serialVersionUID = 1L;

    private long offset;
    private long limit;
    private long total = 0;
    private List<OrderItem> orders = new ArrayList<>();
    private List<T> records = Collections.emptyList();

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    @Override
    @JsonIgnore
    public List<OrderItem> orders() {
        return orders;
    }

    @Override
    @JsonProperty("rows")
    public List<T> getRecords() {
        return records;
    }

    @Override
    public IPage<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    @Override
    public long getTotal() {
        return total;
    }

    @Override
    public IPage<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    @Override
    @JsonIgnore
    public long getSize() {
        return this.limit;
    }

    @Override
    public IPage<T> setSize(long size) {
        this.limit = size;
        return this;
    }

    @Override
    @JsonIgnore
    public long getCurrent() {
        if(offset<=0)return 1;
        return (offset / limit + 1);
    }

    @Override
    public IPage<T> setCurrent(long current) {
        return this;
    }

    @Override
    @JsonIgnore
    public boolean isSearchCount() {
        return true;
    }
}
