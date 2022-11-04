package com.wx.dzz.task.dto;

import lombok.Data;

import java.util.HashMap;

//发送数据集result封装
@Data
public class ResultVo {
    private String touser;
    private String template_id;
    private String topcolor;
    private HashMap<String, DataItem> data;

    private ResultVo(String _touser, String _template_id, String _topcolor, HashMap<String, DataItem> _data) {
        this.touser = _touser;
        this.template_id = _template_id;
        this.topcolor = _topcolor;
        this.data = _data;
    }

    public String getTouser() {
        return touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public HashMap<String, DataItem> getData() {
        return data;
    }

    public static ResultVo initializeResultVo(String _touser, String _template_id, String _topcolor){
        return new ResultVo(_touser,_template_id,_topcolor,null);
    }

    public static ResultVo initializeResultVo(String _touser, String _template_id, String _topcolor,HashMap<String, DataItem> _data){
        return new ResultVo(_touser,_template_id,_topcolor,_data);
    }

    public ResultVo setAttribute(String key, DataItem item){
        if(this.data==null)this.data = new HashMap<String,DataItem>();
        this.data.put(key,item);
        return this;
    }
}
