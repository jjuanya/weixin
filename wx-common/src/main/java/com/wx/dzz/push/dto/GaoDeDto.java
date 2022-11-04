package com.wx.dzz.push.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.regex.Pattern;

@Data
public class GaoDeDto {

    //出生日期
    private String year;

    //名字
    private String name;

    //省份名
    private String province;

    //城市名
    private String city;

    //地区编码
    private Long adcode;

    //风力级别，单位：级
    private String windpower;

    //天气现象
    private String weather;

    //实时气温，单位：摄氏度
    private Long temperature;

    //空气湿度
    private Long humidity;

    //数据发布的时间
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date reporttime;

    //风向描述
    private String winddirection;

}
