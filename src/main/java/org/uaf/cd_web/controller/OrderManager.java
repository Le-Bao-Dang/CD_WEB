package org.uaf.cd_web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.uaf.cd_web.components.Powers;
import org.uaf.cd_web.entity.Orders;
import org.uaf.cd_web.entity.Sold_Pr;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.OrderServiceImp;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class OrderManager {
    public final OrderServiceImp orderServiceImp;

    @Autowired
    public OrderManager(OrderServiceImp orderServiceImp) {
        this.orderServiceImp = orderServiceImp;
    }

    @GetMapping("/listOrder")
    public String listOrder(Model model, HttpSession session, @RequestParam(value = "page", defaultValue = "1") Integer page) {
//        User user = (User) session.getAttribute("auth");
//        if (user == null) {
//            return "redirect:/";
//        }
//        if (user.getDecentralization() != Powers.ADMIN && user.getDecentralization() != Powers.EMPLOYEE)
//            return "redirect:/login";
//        else {
            Page<Orders> ordersPage = orderServiceImp.getListOrder(page);
            model.addAttribute("listOrder", ordersPage.getContent());
            model.addAttribute("totalPage", ordersPage.getTotalPages());
            model.addAttribute("currentPage", page);
            return "OrderManager";

//        }
    }
    @GetMapping("/orderDetail")
    public String orderDetail(Model model, HttpSession session, @RequestParam("id") String id){
            Orders orders = orderServiceImp.getOrderById(id);
            model.addAttribute("order",orders);
        return "detailOrder";
    }
}
