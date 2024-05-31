package org.uaf.cd_web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.uaf.cd_web.entity.Discount;
import org.uaf.cd_web.entity.Product;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.DiscountServiceImp;
import org.uaf.cd_web.services.ProductServiceImp;

import java.util.List;

@Controller
public class ListProduct {
    public final ProductServiceImp productServiceImp;
    public final DiscountServiceImp discountServiceImp;

    @Autowired
    public ListProduct(ProductServiceImp productServiceImp, DiscountServiceImp discountServiceImp) {
        this.productServiceImp = productServiceImp;
        this.discountServiceImp = discountServiceImp;
    }

    @RequestMapping(value = "/listProduct")
    public String showlistProduct(Model model, HttpSession session, @RequestParam("page") Integer pages, @RequestParam("kind") String kind) {
        User user = (User) session.getAttribute("auth");
        List<Product> loveList=null ;
        Page<Product> page = productServiceImp.getListProductInPage(kind, pages);
        List<Discount> listDiscount = discountServiceImp.getListDiscount();
        if (user != null) {
            loveList = productServiceImp.listLikeProduct(user.getIdUser());
        }
//        System.out.println(page.getContent().get(0).getAvt().getUrl());
        model.addAttribute("listPr", page.getContent());
        model.addAttribute("listlike", loveList);
        model.addAttribute("listDiscount", listDiscount);
        model.addAttribute("pageCurrent", pages);
        model.addAttribute("total", page.getTotalPages());
        model.addAttribute("user", user);
        return "list_product";
    }
}
