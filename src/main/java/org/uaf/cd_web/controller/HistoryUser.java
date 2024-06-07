package org.uaf.cd_web.controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.uaf.cd_web.entity.Sold_Pr;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.OrderServiceImp;
import java.util.List;
import java.util.Map;
@Controller
public class HistoryUser {

    @Autowired
    private OrderServiceImp orderServiceImp;

    @GetMapping("/history")
    public String getHistory(HttpSession session, Model model) {
        User user = (User) session.getAttribute("auth");
        String idUser = user.getIdUser();
        List<Sold_Pr> listHistory = orderServiceImp.getHistory(idUser);
        Map<String, List<Sold_Pr>> mapOrders = orderServiceImp.getMapHistoryOrders(listHistory);
        Map<String, Integer> sumOrders = orderServiceImp.sumHistoryOrder(mapOrders);
        System.out.println("history"+listHistory);
        model.addAttribute("sumOrders", sumOrders);
        model.addAttribute("mapOrders", mapOrders);
        model.addAttribute("listHistory", listHistory);
        return "history";
    }


}   
