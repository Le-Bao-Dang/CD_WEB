package org.uaf.cd_web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.uaf.cd_web.component.Encryption;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.UserServiceImp;

import java.sql.Date;

@Controller
public class UpdateAccount {
    private final UserServiceImp userService;

    @Autowired
    public UpdateAccount(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping("/updateAccount")
    public String showUpdateAccount(Model model) {
        System.out.println("getshowupdate");
        return "account";
    }

    @PostMapping("/updateAccount")
    public String updateAccount(@RequestParam("iduser") String iduser,
                                @RequestParam("name") String name,
                                @RequestParam("sex") boolean sex,
                                @RequestParam("birthday") String birthday,
                                @RequestParam("email") String email,
                                @RequestParam("phone") String phone,
                                @RequestParam("passw") String passw,
                                @RequestParam("address") String address,
                                @RequestParam("repassw") String repassw,
                                HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        iduser = user.getIdUser();
        System.out.println(user.getIdUser() + "avc");
        String oldPass = userService.getEncryptPassUser(iduser);
        Date datesignup = userService.getDateSignup(iduser);
        if (!passw.equals(repassw)) {
            redirectAttributes.addFlashAttribute("error", "* Mật khẩu không trùng khớp");
            return "redirect:/account";
        } else if ("".equals(birthday)) {
            redirectAttributes.addFlashAttribute("birthday", "*nhập dô đi ");
            return "redirect:/account";
        } else {
            passw = passw.isEmpty() ? oldPass : Encryption.toSHA1(passw);
            userService.updateAccount(iduser, address, passw, name, phone, email, birthday, datesignup, sex);
            return "redirect:/";
        }


    }
}
