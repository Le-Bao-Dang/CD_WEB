package org.uaf.cd_web.controller;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.IUserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserManager {
    @Autowired
    IUserService userService;

    @GetMapping("/userManager")
    public String getListUser(Model model) {
        List<User> listUser = userService.getListUser();
        model.addAttribute("listUser", listUser);
        return "manager_user";
    }
    @GetMapping("/searchUser")
    public String searchUser(Model model) {
        return "manager_user";
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestParam("idUser") String idUser,
                             @RequestParam("decentralization") int decentralization) {
//        if (user == null) {
//            return "redirect:/";
//        }
//        if (user.getDecentralization() != 2) {
//            return "redirect:/"; // Điều hướng đến trang chính nếu user không phải admin
//        }
        userService.updateUser((byte) decentralization, idUser);
        return "redirect:/admin/userManager";
    }

    public String deleteUser(Model model) {
        return "manager_user";
    }

    @GetMapping("/appearEditUser")
    public String appearEditUser(@RequestParam("idUser") String idUser,
                                 @RequestParam("nameUser") String nameUser,
                                 @RequestParam("passw") String passw,
                                 @RequestParam("email") String email,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("datesignup") String datesignup,
                                 @RequestParam("address") String address,
                                 Model model) {
        model.addAttribute("idUser", idUser);
        model.addAttribute("nameUser", nameUser);
        model.addAttribute("passw", passw);
        model.addAttribute("email", email);
        model.addAttribute("phone", phone);
        model.addAttribute("datesignup", datesignup);
        model.addAttribute("address", address);

        return "edit_user_form";
    }
}
