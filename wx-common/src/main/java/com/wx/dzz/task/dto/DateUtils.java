package com.wx.dzz.task.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * 计算生日天数 days
     *
     * @return
     */
    public Integer getBirthDays(Date birthday) {
        Calendar cToday = Calendar.getInstance(); // 存今天
        Calendar cBirth = Calendar.getInstance(); // 存生日
        Integer days = 0;
        cBirth.setTime(birthday); // 设置生日
        cBirth.set(Calendar.YEAR, cToday.get(Calendar.YEAR)); // 修改为本年
        if (cBirth.get(Calendar.DAY_OF_YEAR) < cToday.get(Calendar.DAY_OF_YEAR)) {
            // 生日已经过了，要算明年的了
            days = (cToday.getActualMaximum(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR)) + cBirth.get(Calendar.DAY_OF_YEAR);
        } else {
            // 生日还没过
            days = cBirth.get(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
        }
        return days;
    }
//
//    /**
//     * 计算恋爱天数 days
//     *
//     * @return
//     */
//    public static int getLoveDays(String loveday) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        int days = 0;
//        try {
//            long time = System.currentTimeMillis() - dateFormat.parse(loveday).getTime();
//            days = (int) (time / (24 * 60 * 60 * 1000));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return days;
//    }
//
//    public static String getRainbow(RestTemplate restTemplate) {
//        String responseJson = restTemplate.getForObject(WeChatConfigure.Rainbow_API, String.class);
//        JSONObject responseResult = JSONObject.parseObject(responseJson);
//        JSONObject jsonObject = responseResult.getJSONArray("newslist").getJSONObject(0);
//        return jsonObject.getString("content");
//    }
//
//    public static Object getAccessToken(RestTemplate restTemplate) {
//        return null;
//    }
}