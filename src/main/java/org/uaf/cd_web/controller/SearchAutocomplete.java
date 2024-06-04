package org.uaf.cd_web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.uaf.cd_web.entity.Product;
import org.uaf.cd_web.services.ProductServiceImp;

import java.util.List;

@Controller
public class SearchAutocomplete {
    @Autowired
    private ProductServiceImp productServiceImp;
    @PostMapping("/search")
    public List<Product> searchProducts(@RequestBody SearchRequest searchRequest) {
        return productServiceImp.searchAutocomplete(searchRequest.getQuery());
    }
}

class SearchRequest {
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }


}
