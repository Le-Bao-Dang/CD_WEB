package org.uaf.cd_web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.UserServiceImp;

import java.util.List;

@Controller
public class ListDetailAccount {

    @Autowired
    private UserServiceImp userService;

    @GetMapping("/account")
    public String listCtAccount(Model model, HttpServletRequest request) {
            HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        System.out.println(user.getIdUser());
        List<User> listDetailAccount = userService.getUserById(user.getIdUser());
        System.out.println(user.getIdUser()+"123");
        model.addAttribute("listDetailAccount", listDetailAccount);
        return "account.html";
    }
}
