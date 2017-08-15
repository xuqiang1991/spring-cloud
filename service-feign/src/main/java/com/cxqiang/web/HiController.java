package com.cxqiang.web;

import com.cxqiang.service.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by xuqiang
 * 2017/8/11.
 */
@Controller
public class HiController {
    @Autowired
    SchedualServiceHi schedualServiceHi;

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String sayHi(@RequestParam String name,Model model){
        String clientOne = schedualServiceHi.sayHiFromClientOne(name);
        model.addAttribute("name",clientOne);
        return "index";
    }
}
