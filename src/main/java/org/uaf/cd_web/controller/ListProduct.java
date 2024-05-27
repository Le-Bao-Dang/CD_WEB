package org.uaf.cd_web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.uaf.cd_web.entity.Product;
import org.uaf.cd_web.services.ProductServiceImp;


@RestController
public class ListProduct {

    @Autowired
    private ProductServiceImp productServiceImp;

    @GetMapping("/products")
    public @ResponseBody  Page<Product> listProduct(Pageable pageable, Model model) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()); // Mỗi trang có 3 sản phẩm
        Page<Product> page = productServiceImp.findAll(pageable);
        return page;
    }
}

