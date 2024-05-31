package org.uaf.cd_web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.uaf.cd_web.components.Powers;
import org.uaf.cd_web.entity.Contact;
import org.uaf.cd_web.entity.Product;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.ContactServiceImp;
import org.uaf.cd_web.services.OrderServiceImp;
import org.uaf.cd_web.services.ProductServiceImp;
import org.uaf.cd_web.services.UserServiceImp;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminHomePage {
    public final ProductServiceImp productServiceImp;
    public final OrderServiceImp orderServiceImp;
    public final ContactServiceImp contactServiceImp;
    public final UserServiceImp userServiceImp;
    int i = 5;

    @Autowired
    public AdminHomePage(ProductServiceImp productServiceImp, OrderServiceImp orderServiceImp, ContactServiceImp contactServiceImp, UserServiceImp userServiceImp) {
        this.productServiceImp = productServiceImp;
        this.orderServiceImp = orderServiceImp;
        this.contactServiceImp = contactServiceImp;
        this.userServiceImp = userServiceImp;
    }

    @GetMapping("/main")
    public String homeAdmin(Model model, HttpSession session) {
//        int i=0;
//        User user = (User) session.getAttribute("auth");
//        if(user!=null){
//            if(user.getDecentralization()==2){
//                i=5;
//            }
//        }
//    return "admin_home";
//        User user = (User) session.getAttribute("auth");
//        if (user != null && user.getDecentralization() == Powers.ADMIN) {

        int newbie = userServiceImp.getNewbie().size();
        String data0 = "" + orderServiceImp.getTurnover(1, 2021);
        String data = "" + orderServiceImp.getTurnover(1, 2022);
        String data1 = "" + orderServiceImp.getTurnover(1, 2023);
        int sumContact = contactServiceImp.getSumContact();
        int tur0 = orderServiceImp.getTurnover(1, 2021);
        int tur = orderServiceImp.getTurnover(1, 2022);
        int tur1 = orderServiceImp.getTurnover(1, 2023);
        int allTur = orderServiceImp.getAllTurnover();
        int saledPR = orderServiceImp.getSalerPR();
        int saledAll = orderServiceImp.getSalerPRAll();
        String stopSaledPR =""+ productServiceImp.getStopPr();
        System.out.println(allTur);
        List<Product> pr = productServiceImp.getListProductHostSale();
        List<Contact> listContact = contactServiceImp.getListContact();

        Collections.sort(listContact, Comparator.comparingInt(Contact::getCondition).reversed());

        model.addAttribute("listContact", listContact);
        model.addAttribute("sumContact", sumContact);

        for (int i = 2; i <= 12; i++) {
            tur0 += orderServiceImp.getTurnover(i, 2021);
            tur += orderServiceImp.getTurnover(i, 2022);
            tur1 += orderServiceImp.getTurnover(i, 2023);
            data0 += "," + orderServiceImp.getTurnover(i, 2021);
            data += "," + orderServiceImp.getTurnover(i, 2022);
            data1 += "," + orderServiceImp.getTurnover(i, 2023);
        }

        int nowTur = orderServiceImp.getTurnover(LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear());

        model.addAttribute("stopSaled", stopSaledPR);
        model.addAttribute("saledPr", saledPR);
        model.addAttribute("saledPrAll", saledAll);
        model.addAttribute("newbie", newbie);
        model.addAttribute("nowTur", nowTur);
        model.addAttribute("tur", tur);
        model.addAttribute("tur1", tur1);
        model.addAttribute("allTur", allTur);
        model.addAttribute("data0", data0);
        model.addAttribute("data", data);
        model.addAttribute("data1", data1);
        model.addAttribute("hotSale", pr);

        return "admin_home";
//        } else {
//            return "redirect:/";
//        }
    }
}
