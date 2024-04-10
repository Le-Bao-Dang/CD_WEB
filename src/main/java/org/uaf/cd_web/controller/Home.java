package org.uaf.cd_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.reponsitory.UserReponesitory;
//import org.uaf.cd_web.entity.User;
//import org.uaf.cd_web.reponsitory.UserReponesitory;

import java.util.List;

@Controller
public class Home {
    @Autowired
    UserReponesitory userReponesitory;
    @RequestMapping(value = "/")
    public String home(Model model){
        List<User> users = userReponesitory.findAll();
        model.addAttribute("list",users);
        return "index";
    }
}
