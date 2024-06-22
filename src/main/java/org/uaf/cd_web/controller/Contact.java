package org.uaf.cd_web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.uaf.cd_web.services.ContactServiceImp;
import org.uaf.cd_web.services.UserServiceImp;
import org.uaf.cd_web.entity.User;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.uaf.cd_web.components.Powers;


@Controller
public class Contact {
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private ContactServiceImp contactServiceImp;

    @GetMapping("/ContactInUser")
    public String getContacts(Model model) {
        return "contact";
    }

    @PostMapping("/ContactInUser")
    public String addContact( @RequestParam(name = "content") String content,
                              @RequestParam(name = "nameuser") String nameuser,
                              @RequestParam(name = "email") String email,
                              @RequestParam(name = "phone") String phone, HttpSession session) {
        User user = (User) session.getAttribute("auth");
        if (user != null) {
            String iduser = user.getIdUser();
            contactServiceImp.addContact(user, content, nameuser, phone, email);
        } else {
            contactServiceImp.addContact(null, content, nameuser, phone, email);
        }
        return "redirect:/";
    }

    @PostMapping("/ContactInAdmin")
    public String viewAndMarkSeen(@RequestParam("idContact") String idContact, @RequestParam("condition") int condition, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("auth");
//        if (user == null || user.getDecentralization() != Powers.ADMIN) {
//            return "redirect:/"; // Redirect to login or home page if user is not authenticated or not admin
//        }
        contactServiceImp.viewContact(idContact);
        contactServiceImp.seenContact(idContact, condition);

        redirectAttributes.addFlashAttribute("message", "Contact viewed and condition updated successfully");
        return "redirect:/admin/main"; // Redirect to AdminMain after processing
    }
}



