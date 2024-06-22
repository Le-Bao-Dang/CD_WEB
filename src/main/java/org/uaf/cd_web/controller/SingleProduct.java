package org.uaf.cd_web.controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.uaf.cd_web.entity.*;
import org.uaf.cd_web.services.FeedBackServiceImp;
import org.uaf.cd_web.services.ProductServiceImp;

import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SingleProduct {
    @Autowired
    private ProductServiceImp  productServiceImp;
    @Autowired
    private FeedBackServiceImp feedBackServiceImp;

    @GetMapping("/oneProduct")
    public String SingleProduct(@RequestParam("id") String idProd, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        List<Detail_Pr> singleProd = productServiceImp.getSingleProduct(idProd);
        List<Image> listURL = productServiceImp.findByIdPr(idProd);
        List<FeedBack> listFeedBack = productServiceImp.getFeedBack(idProd);
        int count = listFeedBack.size() % 3 == 0 ? listFeedBack.size() / 3 : listFeedBack.size() / 3 + 1;
        List<Product> listRelatedProduct = productServiceImp.getRelatedProducts(singleProd.get(0).getProduct().getMenu().getIdMenu());
        Collections.shuffle(listRelatedProduct);
        double avgScore = 0;
        int fullStars = 0;
        if (!listFeedBack.isEmpty()) {
            for (FeedBack f : listFeedBack) {
                avgScore += f.getScorestar();
            }
            avgScore /= listFeedBack.size();
            fullStars = (int) Math.floor(avgScore);
        }

        model.addAttribute("id", idProd);
        model.addAttribute("count", count);
        model.addAttribute("listFeedback", listFeedBack);
        model.addAttribute("listURL", listURL);
        model.addAttribute("avgScore", avgScore);
        model.addAttribute("fullStars", fullStars);
        model.addAttribute("singleProduct", singleProd);
        model.addAttribute("relatedProducts", listRelatedProduct);
        model.addAttribute("user", user);
        return "single_product";
    }
    @GetMapping("/loadMoreComment")
    public String loadMoreComment(@RequestParam("step") int page, @RequestParam("id") String id) {
        List<FeedBack> feedbackList = productServiceImp.getFeedBackInPage(id, page);
        StringBuilder responseContent = new StringBuilder();
        for (FeedBack f : feedbackList) {
            StringBuilder score = new StringBuilder();
            for (int i = 0; i < f.getScorestar(); i++) {
                score.append("<i class=\"fa fa-star\"></i>\n");
            }
            responseContent.append("<div class=\"comment\">\n")
                    .append("    <div class=\"comment-user mt-4\">\n")
                    .append("        <span class=\"comment-name mr-3\">").append(f.getUser().getNameUser()).append("</span>\n")
                    .append("        <span class=\"comment-star\">\n")
                    .append(score)
                    .append("        </span>\n")
                    .append("        <div class=\"content-padding\">\n")
                    .append("            <span class=\"comment-content\">").append(f.getText()).append("</span>\n")
                    .append("        </div>\n")
                    .append("        <span class=\"comment-date\">").append(f.getDate()).append("</span>\n")
                    .append("    </div>\n")
                    .append("</div>");
        }
        return responseContent.toString();
    }
    @GetMapping("/sendComment")
    public ResponseEntity<Map<String, String>> sendComment(@RequestParam("text") String text,
                                                           @RequestParam("idUser") String idUser,
                                                           @RequestParam("idPr") String idProd,
                                                           @RequestParam("star") int star,
                                                           HttpSession session) {
        User user = (User) session.getAttribute("auth");
        idUser=user.getIdUser();
        LocalDateTime dateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        Date date = new java.sql.Date(timestamp.getTime());

        StringBuilder score = new StringBuilder();
        for (int i = 0; i < star; i++) {
            score.append("<i class=\"fa fa-star\"></i>\n");
        }

        // Trả về phản hồi cho client
        Map<String, String> response = new HashMap<>();
        response.put( "comment","<div class=\"comment\">\n" +
                "    <div class=\"comment-user mt-4\">\n" +
                "        <span class=\"comment-name mr-3\">" + user.getNameUser() + "</span>\n" +
                "        <span class=\"comment-star\">\n" +
                score +
                "        </span>\n" +
                "        <div class=\"content-padding\">\n" +
                "            <span class=\"comment-content\">" + text + "</span>\n" +
                "        </div>\n" +
                "        <span class=\"comment-date\">" + date + "</span>\n" +
                "    </div>\n" +
                "</div>");

        // Gọi service để thực hiện việc insert comment vào database
        feedBackServiceImp.insertFeedBack(idProd, idUser, star, text, date);
        return ResponseEntity.ok(response);
    }

}
