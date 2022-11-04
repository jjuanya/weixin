package com.wx.dzz.task;

import com.wx.dzz.push.controller.PushMessageController;
import com.wx.dzz.push.service.UserService;
import com.wx.dzz.push.utlis.User;
import com.wx.dzz.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
//@RestController
//@RequestMapping("/push")
public class TaskTime {

    @Autowired
    PushMessageController pushMessageController;

    @Autowired
    UserService userService;

    private Integer i = 0;
    //每日 早上9点 定时推送
    //todo 添加计算公式，如何测试
    @Scheduled(cron = "0 0 9 * * ?")
//    @GetMapping("/test")
    public R scheduledPush(){

        List<User> list = userService.list(null);
        Map<String, User> userMap = new HashMap<>();
        for (User user:list){
            userMap.put(user.getOpenId(),user);
        }

        try {
            List<String> ls = pushMessageController.getOpenId();
            for (int i=0;i<ls.size();i++){
                System.out.println(i);
                System.out.println(ls.get(i));
                String replace = ls.get(i).replace("\"", "");
                User user = userMap.get(replace);
                pushMessageController.message(user);
            }
            return R.ok("推送成功");
        }catch (Exception e){
            e.printStackTrace();
            return R.fail("推送失败");
        }
    }

}

