package org.uaf.cd_web.controller;

import com.itextpdf.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.uaf.cd_web.components.ExportBillPDF;
import org.uaf.cd_web.components.Format;
import org.uaf.cd_web.components.QRCode;
import org.uaf.cd_web.entity.Discount;
import org.uaf.cd_web.entity.Orders;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.OrderServiceImp;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.thymeleaf.context.Context;

import javax.imageio.ImageIO;

@Controller
@RequestMapping("/admin")
public class OrderManager {
    @Value("${upload.directory}")
    private String uploadDirectory;
    public final OrderServiceImp orderServiceImp;
    private final ExportBillPDF exportBillPDF;

    @Autowired
    public OrderManager(OrderServiceImp orderServiceImp, ExportBillPDF exportBillPDF) {
        this.orderServiceImp = orderServiceImp;
        this.exportBillPDF = exportBillPDF;
    }

    @GetMapping("/listOrder")
    public String listOrder(Model model, HttpSession session,
                            @RequestParam(value = "page", defaultValue = "1") Integer page) {
        User user = (User) session.getAttribute("auth");
        // if (user == null) {
        // return "redirect:/";
        // }
        // if (user.getDecentralization() != Powers.ADMIN && user.getDecentralization()
        // != Powers.EMPLOYEE)
        // return "redirect:/login";
        // else {
        Page<Orders> ordersPage = orderServiceImp.getListOrder(page);
        model.addAttribute("listOrder", ordersPage.getContent());
        model.addAttribute("totalPage", ordersPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "OrderManager";

        // }
    }

    @GetMapping("/orderDetail")
    public String orderDetail(Model model, HttpSession session, @RequestParam("id") String id) {
        Orders orders = orderServiceImp.getOrderById(id);
        int discount = orders.getDiscount() != null ? orders.getDiscount().getValue() : 0;
        int feeShip = orders.getSumOrder() > 200000 ? 20000 : 0;
        model.addAttribute("order", orders);
        model.addAttribute("discount", discount);
        model.addAttribute("feeShip",feeShip);
        model.addAttribute("grandTotal",Format.getToStringPrice((int)orders.getGrandtotal()-feeShip));
        return "detailOrder";
    }

    @GetMapping("/updateOrder")
    public String updateOrder(HttpSession session, @RequestParam("id") String id, @RequestParam("status") Integer status) {
        orderServiceImp.changeConditionOrder(id,status);
        return "redirect:/admin/orderDetail?id=" + id;
    }

    @GetMapping("/searchOrder")
    public String searchOrders(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam("keyword") String keyword) {
        List<Orders> list = orderServiceImp.searchOrder(keyword);
        Page<Orders> ordersPage = orderServiceImp.getListOrder(page);
        model.addAttribute("listOrder", list);
        model.addAttribute("totalPage", ordersPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "OrderManager";
    }

    @GetMapping("/showBill")
    public String ShowBill(@RequestParam("id") String id, Model model) {
        Orders orders = orderServiceImp.getOrderById(id);
        System.out.println(id);
        User user = (User) orders.getUser();
        String qrCodeFilePath = uploadDirectory + orders.getIdOrders() + ".png";
        model.addAttribute("QRcode", qrCodeFilePath);
        model.addAttribute("idOrder", orders.getIdOrders());
        model.addAttribute("dateCreate", Format.formatDateTimeNow(LocalDateTime.now()));
        model.addAttribute("datePay", LocalDateTime.now().toString());
        model.addAttribute("listPr", orders.getPrList());
        String name = orders.getUser() == null ? orders.getCustomers().getName() : user.getNameUser();
        model.addAttribute("name", name);
        String address = orders.getUser() == null ? orders.getCustomers().getAddress() : user.getAddress();
        model.addAttribute("address", address);
        String discount = orders.getDiscount() == null ? "0.000 đ" : orders.getDiscount().getValue() + " đ";
        model.addAttribute("discount", discount);
        model.addAttribute("feeShip", "20.000 đ");
        model.addAttribute("total", orders.getToStringSumOrder() + 'đ');
        return "bill";
    }

    @GetMapping("/Order/exportBill")
    public void exportPdf(HttpServletResponse response, Model model, @RequestParam("id") String id) throws IOException, DocumentException {
        try {
            Orders orders = orderServiceImp.getOrderById(id);
            System.out.println(orders);
            User user = (User) orders.getUser();
            String qrCodeFilePath = uploadDirectory + orders.getIdOrders() + ".png";
            String qrCodeBase64 = QRCode.generateQRCodeImageBase64(orders.getIdOrders(), 200, 200, qrCodeFilePath);
            Context context = new Context();
            context.setVariable("QRcode", qrCodeBase64);
            context.setVariable("idOrder", orders.getIdOrders());
            context.setVariable("dateCreate", Format.formatDateTimeNow(LocalDateTime.now()));
            context.setVariable("datePay", LocalDateTime.now().toString());
            context.setVariable("listPr", orders.getPrList());
            String name = orders.getUser() == null ? orders.getCustomers().getName() : user.getNameUser();
            context.setVariable("name", name);
            String address = orders.getUser() == null ? orders.getCustomers().getAddress() : user.getAddress();
            context.setVariable("address", address);
            String discount = orders.getDiscount() == null ? "0.000 đ" : orders.getDiscount().getValue() + " đ";
            context.setVariable("discount", discount);
            int feeShip = orders.getSumOrder() > 200000 ? 20000 : 0;
            context.setVariable("feeShip", feeShip);
            context.setVariable("total", orders.getToStringSumOrder());
            context.setVariable("grandTotal", Format.getToStringPrice((int) orders.getGrandtotal() - feeShip));
            // Add more variables to the context as needed

            byte[] pdfContents = exportBillPDF.generatePdf("bill", context);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=bill" + id + ".pdf");
            response.setContentLength(pdfContents.length);

            response.getOutputStream().write(pdfContents);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
