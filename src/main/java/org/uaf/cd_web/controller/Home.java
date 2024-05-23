package org.uaf.cd_web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.UserServiceImp;

@Controller
public class Home {
    private
    UserServiceImp userServiceImp;

    @Autowired
    public Home(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @RequestMapping(value = "/")
    public String home(Model model, HttpSession session) {
        User user = (User) session.getAttribute("auth");
        model.addAttribute("auth", user);
        return "index";
    }


}
