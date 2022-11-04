package com.wx.dzz.task.dto;


import lombok.Data;

//单条数据Item封装
@Data
public class DataItem {

    private String value;
    private String color;

    public DataItem(String _value, String _color) {
        this.value = _value;
        this.color = _color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

