package org.uaf.cd_web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.uaf.cd_web.services.CartServiceImp;


@Controller
public class CheckingOut{

    @Autowired
    private CartServiceImp cartServiceImp;

    @PostMapping("/checkingOut")
    public String checkingOut(@RequestParam("sumCheckout") int sum, @RequestParam("discountCheckout") int discount,
            @RequestParam("totalCheckout") int total,
            @RequestParam("allIdProdChecked") String allIdProdChecked,
            @RequestParam("maGiamGia") String maGiamGia,
            Model model) {
//        Map<String, Integer> map;
//        try {
//            map = logistics.getProvince();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        model.addAttribute("maGiamGia", maGiamGia);
        model.addAttribute("sumCheckout", sum);
        model.addAttribute("discountCheckout", discount);
        model.addAttribute("totalCheckout", total);
        model.addAttribute("allIdProdChecked", allIdProdChecked);
//        model.addAttribute("mapProvince", map);
        return "checkout";
    }
}
