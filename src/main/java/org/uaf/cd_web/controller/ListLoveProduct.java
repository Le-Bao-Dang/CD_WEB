package org.uaf.cd_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.uaf.cd_web.entity.Product;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.ProductServiceImp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/loveProduct")
public class ListLoveProduct {

    private final ProductServiceImp productService;

    @Autowired
    public ListLoveProduct(ProductServiceImp productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listLoveProduct(HttpSession session, Model model) {
        User user = (User) session.getAttribute("auth");
//        System.out.println(user.getIdUser() );
        List<Product> listLoveProd = new ArrayList<>();
        if (user == null) { // user chưa đăng nhập
            List<String> loveProductInSession = (List<String>) session.getAttribute("loveProductInSession");
            if (loveProductInSession != null) {
                for (String idProduct : loveProductInSession) {
                    listLoveProd.add(productService.getProductById(idProduct));
                }
            }
        } else {
            listLoveProd = productService.listLoveProduct(user.getIdUser());
            System.out.println(listLoveProd);
        }
        model.addAttribute("listLoveProd", listLoveProd);
        return "love_product";
    }
}
