package org.uaf.cd_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.UserServiceImp;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserManager {
    private final UserServiceImp userService;

    @Autowired
    public UserManager(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping("/userManager")
    public String getListUser(Model model) {
        List<User> listUser = userService.getListUser();
        model.addAttribute("listUser", listUser);
        return "manager_user";
    }

    @GetMapping("/searchUser")
    @ResponseBody
    public List<User> searchUser(Model model,@RequestParam("keyword") String keyword) {
        List<User> listUser = userService.searchUser(keyword);
       return listUser;
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
                                 Model model) {
        User user = userService.getUserByIdUser(idUser);
        model.addAttribute("user", user);


        return "edit_user_form";
    }
}
