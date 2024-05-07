package org.uaf.cd_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.UserServiceImp;

import jakarta.servlet.http.HttpSession;
import org.uaf.cd_web.component.Encryption;

@Controller
public class Login {
    @Autowired
    private UserServiceImp userService;
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login( HttpSession session, Model model, @RequestParam("username") String username,
                        @RequestParam("passw") String passw, RedirectAttributes redirectAttributes) {
      User  user = userService.checkLogin(username);
        passw = Encryption.toSHA1(passw);

        if (user != null) {
            if (user.getDecentralization() == -1) {
                session.setAttribute("auth", user);
                session.setAttribute("idUser", user.getIdUser());
                redirectAttributes.addAttribute("error", "Tài khoản đã bị khoá");
                return "redirect:/login";
            } else if (passw == "" || user.getPassw() == null) {
                redirectAttributes.addFlashAttribute("error", "Vui lòng nhập password");
                return "redirect:/login";
            } else if (user.getPassw().equals(passw)) {
                if (user.getDecentralization() == 1) {
                    session.setAttribute("auth", user);
                    return "redirect:/admin";
                } else if (user.getDecentralization() == 2) {
                    session.setAttribute("auth", user);
                    return "redirect:/employee";
                } else if (user.getDecentralization() == 0) {
                    session.setAttribute("auth", user);
                    session.setAttribute("idUser", user.getIdUser());
                    model.addAttribute("idUser", user.getIdUser());
                    return "redirect:/";
                }
            }
        }
        redirectAttributes.addFlashAttribute("error", "Sai tài khoản hoặc mật khẩu");
            return "redirect:/login";

    }
}