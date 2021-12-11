package com.ego.commons.pojo;

import java.util.List;

public class EasyUIDatagrid {
    private List<?> data;
    private Long total;

    public EasyUIDatagrid() {
    }

    public EasyUIDatagrid(List<?> data, Long total) {
        this.data = data;
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
