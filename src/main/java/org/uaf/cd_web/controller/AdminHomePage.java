package org.uaf.cd_web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.ProductServiceImp;

@Controller
@RequestMapping("/admin")
public class AdminHomePage {
    public final ProductServiceImp productServiceImp;

    @Autowired
    public AdminHomePage(ProductServiceImp productServiceImp) {
        this.productServiceImp = productServiceImp;
    }

    @GetMapping("/home")
    public String homeAdmin(Model model, HttpSession session) {
        int i=0;
        User user = (User) session.getAttribute("auth");
        if(user!=null){
            if(user.getDecentralization()==2){
                i=5;
            }
        }
    return "admin_home";
    }
}
