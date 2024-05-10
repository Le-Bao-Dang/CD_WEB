package org.uaf.cd_web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.uaf.cd_web.entity.Product;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.ProductServiceImp;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/loveProduct")
public class ListLikeProduct {
    @Autowired
    private ProductServiceImp productService;
    @GetMapping
    public String listLoveProd(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        System.out.println(user.getIdUser());
        List<Product> listLikeProd= productService.listLikeProduct(user.getIdUser());
        System.out.println(listLikeProd);
        model.addAttribute("listLikeProd", listLikeProd);
        return "love_product.html";
    }
}
