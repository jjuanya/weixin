package com.wx.dzz.test;

import com.wx.dzz.push.dto.GaoDeDto;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/test")
public class Test {

    @GetMapping("/test")
    public String test(){
        return "Hello Word";
    }


    @SneakyThrows
    public static void main(String[] args) {
        GaoDeDto gaoDeDto = new GaoDeDto();
        List<Field> list = Arrays.asList(gaoDeDto.getClass().getDeclaredFields());
        ArrayList<String> arrayList = new ArrayList<>();
        for (Field f:list){
//            String s = String.valueOf(f);
//            String[] split = s.split("\\.");
//            arrayList.add(split[split.length-1]);
            f.setAccessible(true);
            System.out.println(f.getName()+":"+f.get(gaoDeDto));
        }
        System.out.println(arrayList);
    }

}
