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
    private final ProductServiceImp productServiceImp;
    private final DiscountServiceImp discountServiceImp;

    @Autowired
    public ListProduct(ProductServiceImp productServiceImp, DiscountServiceImp discountServiceImp) {
        this.productServiceImp = productServiceImp;
        this.discountServiceImp = discountServiceImp;
    }

    @RequestMapping(value = "/listProduct")
    public String showListProduct(Model model, HttpSession session,
                                  @RequestParam(value = "page",defaultValue = "1") Integer pages,
                                  @RequestParam("kind") String kind,
                                  @RequestParam(value = "sort") String sort,
                                  @RequestParam(value = "sortDir") String sortDir,
                                  @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        User user = (User) session.getAttribute("auth");
        List<Product> loveList = null;
        Page<Product> page;
        System.out.println("Kind: " + kind);  //
        // Ensure page is within valid range

        try {
                page = productServiceImp.listAllPr(pages, sort, sortDir, kind);
        } catch (Exception e) {
            e.printStackTrace();
            // Add some error handling here
            page = Page.empty();
        }

        List<Discount> listDiscount = discountServiceImp.getListDiscount();
        List<Product> getListPrDiscount = productServiceImp.getListPrDiscount();

        if (user != null) {
            loveList = productServiceImp.listLikeProduct(user.getIdUser());
        }

        model.addAttribute("listPr", page.getContent());
        model.addAttribute("listlike", loveList);
        model.addAttribute("listDiscount", getListPrDiscount);
        model.addAttribute("pageCurrent", pages);
        model.addAttribute("total", page.getTotalPages());
        model.addAttribute("user", user);
        model.addAttribute("sort", sort);
        model.addAttribute("keyword", keyword);
//        model.addAttribute("pageKind", pageKind);

        return "list_product";
    }
}
