package com.wx.dzz.task.dto;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WeChatConfigure {

    public static String Access_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
    public static String Send_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token={0}";

    public static String App_ID;
    @Value("${WeChat.AppID}")
    public void setAppID(String AppID) {
        App_ID = AppID;
    }

    public static String App_Secret;
    @Value("${WeChat.AppSecret}")
    public void setAppSecret(String AppSecret) {
        App_Secret = AppSecret;
    }

//    public static String Open_ID;
//    @Value("${WeChat.OpenID}")
//    public void setOpenID(String OpenID) {
//        Open_ID = OpenID;
//    }
//
//    public static String Template_ID;
//    @Value("${WeChat.TemplateID}")
//    public void setTemplateID(String TemplateID) {
//        Template_ID = TemplateID;
//    }
//
//    public static String Top_Color;
//    @Value("${WeChat.TopColor}")
//    public void setTopColor(String TopColor) {
//        Top_Color = TopColor;
//    }
//
//    public static String Weather_API;
//    @Value("${WeChat.WeatherAPI}")
//    public void setWeatherAPI(String WeatherAPI) {
//        Weather_API = WeatherAPI;
//    }
//
//    public static String Rainbow_API;
//    @Value("${WeChat.RainbowAPI}")
//    public void setRainbowAPI(String RainbowAPI) {
//        Rainbow_API = RainbowAPI;
//    }
//
//    public static String Boy_Birthday;
//    @Value("${WeChat.BoyBirthday}")
//    public void setBoyBirthday(String BoyBirthday) {
//        Boy_Birthday = BoyBirthday;
//    }
//
//    public static String Girl_Birthday;
//    @Value("${WeChat.GirlBirthday}")
//    public void setGirlBirthday(String GirlBirthday) {
//        Girl_Birthday = GirlBirthday;
//    }
//
//    public static String Love_Day;
//    @Value("${WeChat.LoveDay}")
//    public void setLoveDay(String LoveDay) {
//        Love_Day = LoveDay;
//    }
}
