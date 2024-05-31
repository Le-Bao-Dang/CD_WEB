package org.uaf.cd_web.controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.uaf.cd_web.entity.Orders;
import org.uaf.cd_web.entity.Sold_Pr;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.OrderServiceImp;

import java.util.List;
import java.util.Map;

@Controller
public class ManagerOrdersUser {
    @Autowired
    private OrderServiceImp orderServiceImp;

    @GetMapping("/manageOrder")
    public String manageOrder(HttpSession session, Model model) {
        User user = (User) session.getAttribute("auth");
        List<Sold_Pr> listmanageOrder = orderServiceImp.getManagerOrderUser(user.getIdUser());
        Map<String, List<Sold_Pr>> mapOrders = orderServiceImp.getMapHistoryOrders(listmanageOrder);
        Map<String, Integer> sumOrders = orderServiceImp.sumHistoryOrder(mapOrders);
        System.out.println(listmanageOrder);
        model.addAttribute("mapOrder", mapOrders);
        model.addAttribute("sumOrder", sumOrders);

        return "manager_orders_user";
    }
}
