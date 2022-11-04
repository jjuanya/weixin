package com.wx.dzz.push.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wx.dzz.push.dto.GaoDeDto;
import com.wx.dzz.push.dto.WxTemplate;
import com.wx.dzz.push.service.UserService;
import com.wx.dzz.push.utlis.User;
import com.wx.dzz.task.dto.DateUtils;
import com.wx.dzz.task.dto.GaoDe;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping("/push")
public class PushMessageController {

    @Value("${WeChat.AppID}")
    private String appID;

    @Value("${WeChat.AppSecret}")
    private String appSecret;

    @Value("${WeChat.TemplateID}")
    private String templateID;

    @Autowired
    UserService userService;

    //推送消息
    @SneakyThrows
    @PostMapping("/message")
    public Object message(@RequestBody User user) {

        if (user.getRegion() == null || user.getRegion().equals("")) {
            user.setRegion("北京");
        }
        String area = GaoDe.area(user.getRegion());
        String districts = String.valueOf(com.alibaba.fastjson.JSONObject.parseObject(area).get("districts"));
        String replace = districts.replace("[{", "").replace("]}", "");
        replace = "{" + replace + "}";
        String adcode = String.valueOf(com.alibaba.fastjson.JSONObject.parseObject(replace).get("adcode"));
        String weather = GaoDe.weather(Long.valueOf(adcode));
        String lives = String.valueOf(com.alibaba.fastjson.JSONObject.parseObject(weather).get("lives"));
        String rp = lives.replace("[", "").replace("]", "");
        JSONObject jsonObject = JSONObject.parseObject(rp);
        GaoDeDto gaoDeDto = JSONObject.parseObject(String.valueOf(jsonObject), GaoDeDto.class);
        gaoDeDto.setName(user.getName());
        DateUtils utils = new DateUtils();
        Integer days = utils.getBirthDays(user.getYear());
        gaoDeDto.setYear(String.valueOf(days).concat("天"));
        RestTemplate restTemplate = new RestTemplate();
        WxTemplate wxTe = new WxTemplate();
        //将对象转成map
        List<Field> list = Arrays.asList(gaoDeDto.getClass().getDeclaredFields());
        Map<String, Map<String, Object>> map = new HashMap<>();
        for (Field field : list) {
            field.setAccessible(true);
            Map<String, Object> hashMap = new HashMap<>();
            try {
                if (field.getName().equals("reporttime")){
                    hashMap.put("value", new Date());
                }else {
                    hashMap.put("value", field.get(gaoDeDto));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            hashMap.put("color", "#173177");
            map.put(field.getName(), hashMap);
        }
        wxTe.setData(map);
        wxTe.setTouser(user.getOpenId());
        wxTe.setTemplate_id(templateID);
        wxTe.setUrl("www.baidu.com");

        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken();
        restTemplate.postForEntity(url, wxTe, String.class);
        return wxTe;
    }

    @GetMapping("/getOpenId")
    public List<String> getOpenId() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken());//
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "https://api.weixin.qq.com/cgi-bin/user/get?access_token={access_token}", String.class, params
        );

        String body = responseEntity.getBody();
        // 解析成JSONObject 并获取（叫data的JSON对象）JSONObject
        JSONObject object = JSON.parseObject(body).getJSONObject("data");
        // 获取 JSONObject 中的 JSONArray
        JSONArray array = object.getJSONArray("openid");
        String[] split = String.valueOf(array)
                .replace("[", "")
                .replace("]", "")
                .split(",");
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            list.add(split[i]);
        }
        return list;
    }

    @GetMapping("/getAccessToken")
    public String accessToken() {
        RestTemplate restTemplate = new RestTemplate();
        String urlToken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appID + "&secret=" + appSecret;
        //发送请求 得到返回结果
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlToken, String.class);
        String body = responseEntity.getBody();
        String accessToken = String.valueOf(com.alibaba.fastjson.JSONObject.parseObject(body).get("access_token"));
        return accessToken;

//        RestTemplate restTemplate = new RestTemplate();
//        Map<String, String> params = new HashMap<>();
//        params.put("APPID", appID);  //
//        params.put("APPSECRET", appSecret);  //
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
//                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}", String.class, params);
//        String body = responseEntity.getBody();
//        JSONObject object = JSON.parseObject(body);
//        String Access_Token = object.getString("access_token");
//        String expires_in = object.getString("expires_in");// 过期时长（两小时）
//        System.out.println("有效时长expires_in：" + expires_in);
//        return Access_Token;
    }


}
