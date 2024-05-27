package org.uaf.cd_web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.uaf.cd_web.components.Encryption;
import org.uaf.cd_web.services.UserServiceImp;

@Controller
public class SignUp {
    @Autowired
    private UserServiceImp userService;

    @GetMapping("/signup")
<<<<<<< HEAD
    public String showLoginForm(Model model) {
=======
    public String showSignUpForm(Model model) {
>>>>>>> parent of a5c7881 (commit)
        return "signup";
    }

    @PostMapping("/signup")
    public String Signup(HttpSession session, Model model, @RequestParam("name") String name,
                        @RequestParam("email") String email, @RequestParam("phone") String phone,
                        @RequestParam("passw") String passw, @RequestParam("repassw") String repassw,
                        RedirectAttributes redirectAttributes) {
        boolean exist = userService.checkUserExit(email, phone);
        if (name == null) {
            redirectAttributes.addFlashAttribute("errorName", "*Xin hãy nhập tên của bạn");
            return "redirect:/signup";
        }
        if (exist) {
            redirectAttributes.addFlashAttribute("errorAcc", "* Tài khoản đã được sử dụng");
            System.out.println();
            return "redirect:/signup";

        } else if (!passw.equals(repassw)) {
            redirectAttributes.addFlashAttribute("errorDup", "* Mật khẩu không trùng khớp");
            return "redirect:/signup";
        } else {
            String password = Encryption.toSHA1(passw);
            userService.createUser(name, phone, email, password);
            return ("redirect:/");
        }


    }


}
