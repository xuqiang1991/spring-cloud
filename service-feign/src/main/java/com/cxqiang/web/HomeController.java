package com.cxqiang.web;

import com.cxqiang.entity.sys.SysMenu;
import com.cxqiang.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by xuqiang
 * 2017/8/24.
 */
@Controller
public class HomeController {

    @Value("${sys.appId}")
    private String appId;

    @Autowired
    SysService sysService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(Model model){
        List<SysMenu> sysMenus = sysService.findMenuByApp(appId);
        model.addAttribute("menus", sysMenus);
        return "index";
    }

    @RequestMapping(value = "/context",method = RequestMethod.GET)
    public String context(){
        return "context";
    }


    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return "test";
    }

}
