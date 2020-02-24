package com.pctc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeLeafController {

    @RequestMapping("/index")
    public String index() {
      
        return "index";
    }
    
    
    
    @RequestMapping("/show")
    public String showInfo(Model model) {
        model.addAttribute("msg", "我是第一个thymeleaf案例");
        model.addAttribute("sex","女");
        
        List<String> nameList = new ArrayList<String>();
        nameList.add("zhangsan");
        nameList.add("lisi");
        nameList.add("wangwu");

        model.addAttribute("list", nameList);

        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "张三");
        map.put("2", "李四");
        map.put("3", "王五");
        model.addAttribute("map", map);
        
        model.addAttribute("date",new Date());
   

        return "thymeleafDemo";
    }
    
    
}
