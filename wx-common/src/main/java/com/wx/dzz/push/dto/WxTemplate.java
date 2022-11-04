package com.wx.dzz.push.dto;

import lombok.Data;

import java.util.Map;

@Data
public class WxTemplate {

    //进行推送所需参数
    private String touser;//用户openid（接收人）
    private String template_id;//消息模版id
    private String url = "https://baidu.com";//跳转到某链接或某页面
    private Map<String,Map<String,Object>> data;//要推送的模板内容

}
