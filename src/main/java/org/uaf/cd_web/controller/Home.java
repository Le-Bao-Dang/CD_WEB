package org.uaf.cd_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.UserServiceImp;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class Home {
    @Autowired
    UserServiceImp userServiceImp;
    @RequestMapping(value = "/")
    public String home(Model model){
        User user = userServiceImp.getUserById("");

        model.addAttribute("auth",user);
        return "index";
    }
}
