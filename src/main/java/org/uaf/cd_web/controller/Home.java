package org.uaf.cd_web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.CartServiceImp;
import org.uaf.cd_web.services.UserServiceImp;

@Controller
public class Home {
    private UserServiceImp userServiceImp;
    private final CartServiceImp cartServiceImp;

    @Autowired
    public Home(UserServiceImp userServiceImp, CartServiceImp cartServiceImp) {
        this.userServiceImp = userServiceImp;
        this.cartServiceImp = cartServiceImp;
    }

    @RequestMapping(value = "/")
    public String home(Model model, HttpSession session) {
        User user = (User) session.getAttribute("auth");
        int sumCart =0;
        if(user!=null) {
             sumCart = cartServiceImp.getListCart(user.getIdUser()).size();
        }
        model.addAttribute("auth", user);
        model.addAttribute("sumCart", sumCart);
        return "index";
    }


}
