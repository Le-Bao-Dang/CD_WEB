package org.uaf.cd_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.uaf.cd_web.entity.Cart;
import org.uaf.cd_web.entity.Product;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.CartServiceImp;
import org.uaf.cd_web.services.ProductServiceImp;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ListLoveProduct {
    @Autowired
    private ProductServiceImp productServiceImp;
    @Autowired
    private CartServiceImp cartServiceImp;

    @GetMapping("/loveProduct")
    public String getLoveProduct(HttpSession session, Model model) {
        User user = (User) session.getAttribute("auth");
        List<Product> listLoveProd = productServiceImp.listLikeProduct(user.getIdUser());
        System.out.println("AAAA" + listLoveProd);
        model.addAttribute("listLoveProd", listLoveProd);
        return "love_product";
    }

    @GetMapping("/addToCart")
    @ResponseBody
    public String addToCart(@SessionAttribute(value = "auth", required = false) User user,
                            @RequestParam("idPr") String idPr,
                            @RequestParam("amount") int amount,
                            HttpSession session) {

        int sumCart = 0;
        int newSumCart = 0;

            List<Cart> listCart = cartServiceImp.getListCart(user.getIdUser());
            sumCart = cartServiceImp.sumAmount(listCart);
            newSumCart = sumCart + amount;
            if (!cartServiceImp.checkExit(user.getIdUser(), idPr)) {
                cartServiceImp.insertToCart(user.getIdUser(), idPr, amount);

            } else {
                cartServiceImp.updateToCart(user.getIdUser(), idPr, amount);
            }

        session.setAttribute("sumCart", newSumCart);

        return "<a href=\"/cart\" class=\"nav-link\">\n" +
                "<span class=\"fa-solid fa-cart-shopping\"></span>[" + newSumCart + "]</a>";
    }

    @GetMapping("/removeFromLove")
//    @DeleteMapping("/removeFromLove")
    public void removeFromLove(@RequestParam("idPr") String idPr, HttpSession session) {
        User user = (User) session.getAttribute("auth");
        if (user != null) {
            String idUser = user.getIdUser();
            cartServiceImp.deleteFromLove(idPr, idUser);
        }
    }


}
