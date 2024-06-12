package org.uaf.cd_web.controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.uaf.cd_web.entity.*;
import org.uaf.cd_web.services.ProductServiceImp;

import java.util.Collections;
import java.util.List;

@Controller
public class SingleProduct {


    @Autowired
    private ProductServiceImp  productServiceImp;

    @GetMapping("/oneProduct")
    public String getOneProduct(@RequestParam("id") String idProd, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        List<Detail_Pr> singleProd = productServiceImp.getSingleProduct(idProd);
        System.out.println(singleProd);
        List<Image> listURL = productServiceImp.findByIdPr(idProd);
        List<FeedBack> listFeedBack = productServiceImp.getFeedBack(idProd);
        int count = listFeedBack.size() % 3 == 0 ? listFeedBack.size() / 3 : listFeedBack.size() / 3 + 1;
        List<Product> listRelatedProduct = productServiceImp.getRelatedProducts(singleProd.get(0).getProduct().getMenu().getIdMenu());
        Collections.shuffle(listRelatedProduct);

        model.addAttribute("id", idProd);
        model.addAttribute("count", count);
        model.addAttribute("listFeedback", listFeedBack);
        model.addAttribute("listURL", listURL);
        model.addAttribute("singleProduct", singleProd);
        model.addAttribute("relatedProducts", listRelatedProduct);

        return "single_product";
    }
}
