package org.uaf.cd_web.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.uaf.cd_web.components.Excel;
import org.uaf.cd_web.components.ImportFormExcel;
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
    public String getListUser(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        List<User> listUser = userService.getListUser();
        Page<User> userPage = userService.getListUserPage(page);
        model.addAttribute("listUser", listUser);
        model.addAttribute("count", userPage.getTotalPages());
        model.addAttribute("page", page);
        return "manager_user";
    }

    @GetMapping("/searchUser")
    @ResponseBody
    public List<User> searchUser(Model model, @RequestParam("keyword") String keyword) {
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

    // dường dẫn đễ tải file
    @GetMapping(value = "/ExportUserExcel")
    public View dowload(Model mm) {
        List<User> listEmployee = userService.getListEmployee();
        mm.addAttribute("employes", listEmployee);
        return (View) new Excel();
    }

    @PostMapping("/uploadForUser")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ImportFormExcel.hasExcelFormat(file)) {
            try {
                userService.saveFromExcel(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return message;
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!" + e.getMessage();
                return message;
            }
        }
        message = "Please upload an excel file!";
        return message;
    }
}
