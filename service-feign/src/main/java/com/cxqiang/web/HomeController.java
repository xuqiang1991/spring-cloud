package com.cxqiang.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by xuqiang
 * 2017/8/24.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String sayHi(@RequestParam String name, Model model){
        model.addAttribute("name",name);
        return "index";
    }

    @RequestMapping(value = "/context",method = RequestMethod.GET)
    public String context(){
        return "context";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return "test";
    }
}
