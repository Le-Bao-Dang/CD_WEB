package org.uaf.cd_web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.uaf.cd_web.entity.Cart;
import org.uaf.cd_web.entity.Product;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.CartServiceImp;
import org.uaf.cd_web.services.ProductServiceImp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ListCart {

    @Autowired
    private CartServiceImp cartServiceImp;
    private ProductServiceImp productServiceImp;

    @GetMapping( "/cart")
    public String ListCart(HttpSession session, Model model) {
        User user = (User) session.getAttribute("auth");
        List<Cart> listCart = cartServiceImp.getListCart(user.getIdUser());
        System.out.println(listCart);
        int sum = cartServiceImp.sumCart(listCart);
        model.addAttribute("listCart", listCart);
        model.addAttribute("sum", sum);
        return "cart";
//        Map<String, Integer> listProductFromCartInSession = (Map<String, Integer>)
//                session.getAttribute("listProductFromCartInSession");
//        if(user == null) {  // user chưa đăng nhập
//            List<Product> listProduct = new ArrayList<>();
//            listCart = new ArrayList<>();
//            if(listProductFromCartInSession != null) {
//                for (String idProduct : listProductFromCartInSession.keySet()) {
//                    listProduct.add(productServiceImp.getProductById(idProduct));
//                }
//                for (Product p : listProduct) {
//                    listCart.add(new Cart(user.getIdUser(),p.getIdPr(),listProductFromCartInSession.get(p.getIdPr()),  p, user ));
//                }
//            }
//        } else {
//        }

    }
}