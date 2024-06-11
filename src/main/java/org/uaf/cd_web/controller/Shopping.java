package org.uaf.cd_web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.uaf.cd_web.components.MailSender;
import org.uaf.cd_web.entity.*;
import org.uaf.cd_web.services.*;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class Shopping {
    private final CartServiceImp cartServiceImp;
    private final OrderServiceImp orderServiceImp;
    private final UserServiceImp userServiceImp;
    private final ProductServiceImp productServiceImp;
    private final DiscountServiceImp discountServiceImp;
    private final SoldPrServiceImp soldPrServiceImp;

    @Autowired
    public Shopping(CartServiceImp cartServiceImp, OrderServiceImp orderServiceImp, UserServiceImp userServiceImp, ProductServiceImp productServiceImp, DiscountServiceImp discountServiceImp, SoldPrServiceImp soldPrServiceImp) {
        this.cartServiceImp = cartServiceImp;
        this.orderServiceImp = orderServiceImp;
        this.userServiceImp = userServiceImp;
        this.productServiceImp = productServiceImp;
        this.discountServiceImp = discountServiceImp;
        this.soldPrServiceImp = soldPrServiceImp;
    }

    //    @GetMapping("cart")
//    public String getListCart(HttpSession session, Model model) {
//        User user = (User) session.getAttribute("auth");
//        List<Cart> listCart = cartServiceImp.getListCart(user.getIdUser());
//        int sum = cartServiceImp.sumCart(listCart);
//        model.addAttribute("listCart", listCart);
//        model.addAttribute("sum", sum);
//        return "cart";
//    }
    //Thêm sản phẩm vào giỏ hàng
    @GetMapping("/addToCart")
    public String addCart(Model model, @RequestParam("idPr") String idPr, @RequestParam("amount") Integer amount, HttpSession session) {
        User user = (User) session.getAttribute("auth");
        if (user == null) {
            return "/";
        }
        Cart cart = new Cart(user.getIdUser(), idPr, amount);
        cartServiceImp.addCart(cart);
        return "cart";
    }

    // Bỏ chọn sản phẩm trong giỏ hàng
    @GetMapping("/unCheckFromCart")
    @ResponseBody
    public String unCheckFromCart(
            @RequestParam("id") String id,
            @RequestParam("sum") int sum,
            @RequestParam("discount") int discount,
            @RequestParam("total") int total,
            @RequestParam("listId") String listId,
            HttpSession session) {

        DecimalFormat dec = new DecimalFormat("#,###");
        String sumF = dec.format(sum).replace(',', '.');
        String discountF = dec.format(discount).replace(',', '.');
        String totalF = dec.format(total).replace(',', '.');

        User user = (User) session.getAttribute("auth");
        String idUser = (user != null) ? user.getIdUser() : "";

        return amountForm(sum, sumF, discount, discountF, listId);
    }

    // Xóa sản phẩm khỏi giỏ hàng
    @GetMapping("/removeFromCart")
    @ResponseBody
    public String removeFromCart(@RequestParam("id") String id,
                                 @RequestParam("sum") int sum,
                                 @RequestParam("discount") int discount,
                                 @RequestParam("total") int total,
                                 @RequestParam("listId") String listId,
                                 HttpSession session, Model model) {

        DecimalFormat dec = new DecimalFormat("#,###");
        String sumF = dec.format(sum).replace(',', '.');
        String discountF = dec.format(discount).replace(',', '.');
        String totalF = dec.format(total).replace(',', '.');

        User user = (User) session.getAttribute("auth");
        List<Cart> listCart;

        if (user == null) { // User is not logged in
            listCart = new ArrayList<>();
            Map<String, Integer> listProductFromCartInSession = (Map<String, Integer>) session.getAttribute("listProductFromCartInSession");
            listProductFromCartInSession.remove(id);
            Product product;
            for (String idProduct : listProductFromCartInSession.keySet()) {
                product = productServiceImp.getProductById(idProduct);
//                listCart.add(new Cart(product.getIdPr(), product.getDiscount(), product.getPrice(), product.getNamePr(), product.getAvt().getUrl(), listProductFromCartInSession.get(idProduct)));
            }
        } else {
            cartServiceImp.deleteFromCart(id, user.getIdUser());
            listCart = cartServiceImp.getListCart(user.getIdUser());
        }

        session.removeAttribute("sumCart");
        int sumCart = cartServiceImp.sumCart(listCart);
        session.setAttribute("sumCart", sumCart);

        return amountForm(sum, sumF, discount, discountF, listId);
    }

    //Kiểm tra số lượng sản phẩm
    @GetMapping("/checkInventory")
    @ResponseBody
    public String checkInventory(@RequestParam("id") String id) {
        String re = "";
        int inventory = productServiceImp.getProductById(id).getDetailPr().getInventory();
        re += inventory;
        return re;
    }

    //Thay đổi tổng giá giỏ hàng
    @GetMapping("/changeAmountFormCart")
    @ResponseBody
    public String changeAmountCart(@RequestParam("id") String idProduct,
                                   @RequestParam("sum") int sum,
                                   @RequestParam("discount") int discount,
                                   @RequestParam("total") int total,
                                   @RequestParam("listId") String listId,
                                   @RequestParam("amount") int amount,
                                   HttpSession session,
                                   Model model) {
        DecimalFormat dec = new DecimalFormat("#,###");
        String sumF = dec.format(sum).replace(',', '.');
        String discountF = dec.format(discount).replace(',', '.');
        String totalF = dec.format(total).replace(',', '.');

        User user = (User) session.getAttribute("auth");

        if (user == null) {
            Map<String, Integer> listProductFromCartInSession = (Map<String, Integer>) session.getAttribute("listProductFromCartInSession");
            if (listProductFromCartInSession != null) {
                listProductFromCartInSession.put(idProduct, amount);
            }
        } else {
            cartServiceImp.updateAmountToCart(idProduct, user.getIdUser(), amount);
        }

        return amountForm(sum, sumF, discount, discountF, listId);
    }

    //Áp dụng mã giảm giá
    @GetMapping("/applyDiscount")
    @ResponseBody
    public String applyDiscount(@RequestParam("code") String code,
                                @RequestParam("sum") int sum,
                                @RequestParam("discount") int discount,
                                @RequestParam("listId") String listId) {
        DecimalFormat dec = new DecimalFormat("#,###");
        Discount discounts = discountServiceImp.getDiscountByCode(code);

        if (discounts.getSoluong() == 0) {
            return "3";  // Mã giảm giá không tồn tại
        } else {
            if (discounts.getSoluong() <= 0) {
                return "0";  // Hết số lượng mã giảm giá
            } else if (discounts.getMinium() > sum) {
                return "1";  // Không đủ điều kiện tối thiểu để áp dụng mã giảm giá
            } else if (discount > 0) {
                return "2";  // Đã áp dụng mã giảm giá trước đó
            } else {
                int newTotal;
                int newDiscount;
                if (discounts.isType()) {
                    newDiscount = (sum * discounts.getValue()) / 100;
                    newTotal = sum - newDiscount;
                } else {
                    newDiscount = discounts.getValue();
                    newTotal = sum - newDiscount;
                }
                String sumF = dec.format(sum).replace(',', '.');
                String discountF = dec.format(newDiscount).replace(',', '.');
                String totalF = dec.format(newTotal).replace(',', '.');
                return amountForm(sum, sumF, discount, discountF, listId);
            }
        }
    }

    //Hủy áp dụng mã giảm giá
    @GetMapping("/canelApplyDiscount")
    @ResponseBody
    public String cacelApplyDiscount(@RequestParam("sum") int sum,
                                     @RequestParam("discount") int discount,
                                     @RequestParam("listId") String listId) {

        DecimalFormat dec = new DecimalFormat("#,###");
        int newDisCount = 0;
        int newTotal = sum;

        String sumF = dec.format(sum).replace(',', '.');
        String discountF = dec.format(newDisCount).replace(',', '.');
        String totalF = dec.format(newTotal).replace(',', '.');
        return amountForm(sum, sumF, discount, discountF, listId);
    }

    //Hiển thị trang thanh toán
    @PostMapping("/checkout")
    public String checkout(@RequestParam("sumCheckout") int sum,
                           @RequestParam("discountCheckout") int discount,
                           @RequestParam("totalCheckout") int total,
                           @RequestParam("allIdProdChecked") String allIdProdChecked,
                           @RequestParam(value = "maGiamGia", required = false) String maGiamGia,
                           HttpSession session, Model model) {
        int sums = sum;
        User user = (User) session.getAttribute("auth");
        model.addAttribute("user", user);
        model.addAttribute("maGiamGia", maGiamGia);
        model.addAttribute("sum", sums);
        model.addAttribute("discount", discount);
        model.addAttribute("total", total);
        model.addAttribute("allIdProdChecked", allIdProdChecked);
        return "checkout";

    }

    //Thực hiện thanh toán đơn hàng
    @GetMapping("/pay")
    public String createOrder(HttpSession session,
                              @RequestParam("fullName") String fullName,
                              @RequestParam("phoneNumber") String phoneNumber,
                              @RequestParam("email") String email,
                              @RequestParam("address") String address,
                              @RequestParam("note") String note,
                              @RequestParam("payment") String payment,
                              @RequestParam("listId") String listId,
                              @RequestParam("maGiamGia") String maGiamGia,
                              @RequestParam("totalCheckout") String totalCheckout,
                              Model model) {

        User user = (User) session.getAttribute("auth");
        String idUser;
        LocalDateTime timeNow = LocalDateTime.now();
        LocalDateTime timePickup = timeNow.plusDays(3);
        String[] listIdArray = listId.trim().replace("box", "").split(" ");
        System.out.println(listId);
        System.out.println(Arrays.toString(listIdArray));
        List<Cart> listCart;
        //addOrder
        String idOrder = orderServiceImp.generateIdOrder();
        Discount discount = discountServiceImp.getDiscountByCode(maGiamGia);

//        if (user == null) {
//            Customers customers = new Customers(fullName, phoneNumber, address, email);
//            Orders orders = new Orders(idOrder, null, discount, customers, timeNow, null, note, 0);
//            orderServiceImp.addOrder(orders);
//            if (!maGiamGia.isEmpty()) {
//                discountServiceImp.subttractQuantity(maGiamGia);
//            }
//            idUser = null;
//            Map<String, Integer> listProductFromCartInSession = (Map<String, Integer>) session.getAttribute("listProductFromCartInSession");
//            for (String idProduct : listIdArray) {
//                Product product = productServiceImp.getProductById(idProduct);
//                Sold_Pr soldPr = new Sold_Pr(product, timeNow, listProductFromCartInSession.get(idProduct), orders, product.getPrice());
//                soldPrServiceImp.addToSoldProd(soldPr);
//                productServiceImp.updateInventoryCT_PR(idProduct, -listProductFromCartInSession.get(idProduct));
//                listProductFromCartInSession.remove(idProduct);
//            }
//            session.setAttribute("listProductFromCartInSession", listProductFromCartInSession);
//        } else {
        Orders orders = new Orders(idOrder, user, discount, null, timeNow, timePickup, note, 0);
        System.out.println(orders);
        orderServiceImp.addOrder(orders);
        if (!maGiamGia.isEmpty()) {
            discountServiceImp.subttractQuantity(maGiamGia);
        }
        idUser = user.getIdUser();
        listCart = cartServiceImp.getProdFormCart(idUser, listIdArray);
        for (Cart c : listCart) {
            Sold_Pr soldPr = new Sold_Pr(c.getProduct(), timeNow, c.getAmount(), orders, c.getProduct().getPrice());
            System.out.println(soldPr);
            soldPrServiceImp.addToSoldProd(soldPr);
            productServiceImp.updateInventoryCT_PR(c.getIdPr(), -c.getAmount());
            cartServiceImp.deleteFromCart(c.getIdPr(), idUser);
        }
//        }

        String content = generateOrderEmailContent(fullName, phoneNumber, address, timePickup.toString(), orders.getIdOrders(), totalCheckout);
        MailSender.send("Cảm ơn bạn đã mua hàng", content, email);

//        logOrderSuccess(idUser, listId, totalCheckout, session);

        return "redirect:/";
    }

    private String generateOrderEmailContent(String name, String phone, String address, String timePickup, String idOrder, String totalOrder) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "    <style>\n" +
                "        .container {\n" +
                "            border: 1px solid black;\n" +
                "            max-width: 500px;\n" +
                "            background: rgb(224, 221, 221);\n" +
                "        }\n" +
                "        .title {\n" +
                "            color: cadetblue;\n" +
                "        }\n" +
                "        p {\n" +
                "          display: inline-block;  \n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <h2>Cảm ơn bạn đã tin tưởng và mua hàng ở cửa hàng của chúng tôi, dưới đây là thông tin về đơn hàng của bạn</h2>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"item\">\n" +
                "            <p class=\"title\">Tên: </p>\n" +
                "            <p>" + name + "</p>\n" +
                "        </div>\n" +
                "        <div class=\"item\">\n" +
                "            <p class=\"title\">Số điện thoại: </p>\n" +
                "            <p>" + phone + "</p>\n" +
                "        </div>\n" +
                "        <div class=\"item\">\n" +
                "            <p class=\"title\">Id Đơn hàng: </p>\n" +
                "            <p>" + idOrder + "</p>\n" +
                "        </div>\n" +
                "        <div class=\"item\">\n" +
                "            <p class=\"title\">Giá trị đơn hàng: </p>\n" +
                "            <p>" + totalOrder + "</p>\n" +
                "        </div>\n" +
                "        <div class=\"item\">\n" +
                "            <p class=\"title\">Địa chỉ: </p>\n" +
                "            <p>" + address + "</p>\n" +
                "        </div>\n" +
                "        <div class=\"item\">\n" +
                "            <p class=\"title\">Thời gian dự kiến nhận hàng: </p>\n" +
                "            <p>" + timePickup + "</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }
//    private void logOrderSuccess(String idUser, String allId, String totalCheckout, HttpSession session) {
//        Log log = new Log(Log.INFO, idUser, "Pay",
//                "Pay success: " + allId + ", total:" + totalCheckout, 0,
//                Brower.getBrowerName(session.getHeader("User-Agent")),
//                Brower.getLocationIp(session.getRemoteAddr()));
//        DB.me().insert(log);
//    }

    private String amountForm(int sum, String sumF, int discount, String discountF, String listId) {
        DecimalFormat dec = new DecimalFormat("#,###");
        return "<h5>Tổng giỏ hàng</h5>\n" +
                "\t\t\t\t\t<ul>\n" +
                "\t\t\t\t\t\t<li id=\"sum\" value=\"" + sum + "\">Tổng tiền hàng\n" +
                "<input name=\"sumCheckout\" value=\"" + sum + "\" style=\"display: none\">" +
                "\t\t\t\t\t\t\t<span>" + sumF + "đ</span>\n" +
                "\t\t\t\t\t\t</li>\n" +
                "\t\t\t\t\t\t<li id=\"discount\" value=\"" + discount + "\">Giảm" +
                "<input name=\"discountCheckout\" value=\"" + discount + "\" style=\"display: none\" >" +
                " <span >" + discountF + "đ</span></li>\n" +
                "\t\t\t\t\t\t<li id=\"total\" value=\"" + (sum - discount) + "\">Tổng thanh toán (chưa gồm phí vận chuyển)" +
                "<input name=\"totalCheckout\" value=\"" + (sum - discount) + "\" style=\"display: none\" >" +
                " <span>" + dec.format(sum - discount).replace(',', '.') + "đ</span></li>\n" +
                "\t\t\t\t\t</ul>\n" +
                "<input id=\"idProdChecked\" type=\"text\" name=\"allIdProdChecked\" value=\"" + listId + "\" style=\"display: none\">" +
                "\t\t\t\t\t<button type=\"submit\" class=\"primary-btn\">Thanh toán</button>";
    }
}

