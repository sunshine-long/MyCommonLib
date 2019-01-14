package com.marlon.module.common.javabean;

import java.io.Serializable;
import java.util.List;

/**
 * @desc Record
 * @author Marlon
 * @date 2019/1/9
 */
public class Record<T> implements Serializable {
    private List<T> record;
    private String size;

    public List<T> getRecord() {
        return record;
    }

    public void setRecord(List<T> record) {
        this.record = record;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
