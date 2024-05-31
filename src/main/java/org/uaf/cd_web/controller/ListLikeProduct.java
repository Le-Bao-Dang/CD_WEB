package org.uaf.cd_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.uaf.cd_web.entity.Product;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.ProductServiceImp;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ListLikeProduct {
    @Autowired
    private  ProductServiceImp productServiceImp;

    @GetMapping("/loveProduct")
    public String getLoveProduct(HttpSession session, Model model) {
        User user = (User) session.getAttribute("auth");
        System.out.println(user);
        List<Product> listLoveProd= productServiceImp.listLikeProduct(user.getIdUser());
        System.out.println("AAAA"+listLoveProd);
        model.addAttribute("listLoveProd", listLoveProd);
        return "love_product"; // Trả về tên của view (loveProduct.jsp hoặc loveProduct.html)
    }
}
