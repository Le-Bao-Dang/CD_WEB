package org.uaf.cd_web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.uaf.cd_web.components.RandomOTP;
import org.uaf.cd_web.entity.Detail_Pr;
import org.uaf.cd_web.entity.Image;
import org.uaf.cd_web.entity.Product;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.ProductServiceImp;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.nio.file.*;

@Controller
@RequestMapping("/admin")
public class ProductManager {
    InputStream inputStream = null;
    @Value("${upload.directory}")
    private String uploadDirectory;
    private static final long serialVersionUID = 1;
    private ProductServiceImp productServiceImp;

    @Autowired
    public ProductManager(ProductServiceImp productServiceImp) {
        this.productServiceImp = productServiceImp;
    }

    @GetMapping("/productManager")
    public String getListProduct(Model model, HttpSession session,
                                 @RequestParam("page") String page) {
        User user = (User) session.getAttribute("auth");
//        if (user == null) {
//            return "redirect:/";
//        }
        int pages = Integer.parseInt(page);
        int tempSize = productServiceImp.getSize() / 15;
        int count = productServiceImp.getSize() % 15 > 0 ? tempSize + 1 : tempSize;
        List<Product> list = productServiceImp.getListProductInPage(pages);
        System.out.println(list.get(0).getImage());
        model.addAttribute("listProduct", list);
        model.addAttribute("page", pages);
        model.addAttribute("count", count);
        return "manager_product";
    }

    @PostMapping("/addProduct")
    public String add(@RequestParam("menu") String menu,
                      @RequestParam("discount") String discounts,
                      @RequestParam("price") String prices,
                      @RequestParam("name") String name,
                      @RequestParam("nsx") String nsx,
                      @RequestParam("hsd") String hsd,
                      @RequestParam("brand") String brand,
                      @RequestParam("mota") String mota,
                      @RequestParam("weight") String weights,
                      @RequestParam("origin") String origin,
                      @RequestParam("inventory") String inventoreis,
                      @RequestParam("imageFiles") MultipartFile[] imageFiles,
                      Model model, HttpSession session) throws IOException {
        int price = Integer.parseInt(prices);
        double weight = Double.parseDouble(weights);
        int inventory = Integer.parseInt(inventoreis);
        int discount = Integer.parseInt(discounts);

        User user = (User) session.getAttribute("auth");
//        if (user == null) {
//            return "/";
//        }

        // Thực hiện lưu sản phẩm
        int index = productServiceImp.getListProduct().size() + 1;
        Detail_Pr detailPr = new Detail_Pr("", Date.valueOf(nsx), Date.valueOf(hsd), brand, mota, weight, origin, Date.valueOf(LocalDate.now()), inventory, 0);
        Product product = new Product("", menu, discount, price, name, detailPr);
        productServiceImp.add(product);
        // Lưu các tệp hình ảnh
        int count = 0;
        Image image;
        for (MultipartFile file : imageFiles) {
            if (file.isEmpty()) {
                continue;
            }
            try {
                // Lưu file vào thư mục
                byte[] bytes = file.getBytes();
                String fileName = file.getOriginalFilename();
                Path path = Paths.get(uploadDirectory + "/" + fileName);
                Files.write(path, bytes);

                // Lưu thông tin file vào cơ sở dữ liệu
                String fileUrl = "/static/ImageproductNew/add/" + fileName;
                count++;
                String idImg = menu + brand + count + RandomOTP.generateRandomString();
                image = new Image("prod" + index, idImg, fileUrl, 1);
                productServiceImp.addImgforProduct(image);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/admin/productManager?&page=1";
    }

    @GetMapping("/formEdit")
    public String formEdit(Model model, @RequestParam("id") String id) {

//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("auth");
//        if (user == null) {
//            return "/";
//        }
//        if (user.getDecentralization() != 2 && user.getDecentralization() != 1) {
//            return "/";
//        }
        Product product = productServiceImp.getProductById(id);
//        Map<String, Integer>view = productServiceImp.getQuantity();
        model.addAttribute("product", product);
        return "form_edit_product";
    }

    @PostMapping("/editImg")
    public String editImg(@RequestParam("id") String idPr,
                          @RequestParam("imageFiles") MultipartFile[] imageFiles,
                          Model model) {
        Product pr = productServiceImp.getProductById(idPr);
        // Lưu các tệp hình ảnh
        int count = 0;
        Image image;
        for (MultipartFile file : imageFiles) {
            if (file.isEmpty()) {
                continue;
            }
            try {
                // Lưu file vào thư mục
                byte[] bytes = file.getBytes();
                String fileName = file.getOriginalFilename();
                Path path = Paths.get(uploadDirectory + "/" + fileName);
                Files.write(path, bytes);

                // Lưu thông tin file vào cơ sở dữ liệu
                String fileUrl = "/static/ImageproductNew/add/" + fileName;
                count++;
                String idImg = pr.getNamePr() + count + RandomOTP.generateRandomString();
                image = new Image(idPr, idImg, fileUrl, 1);
                productServiceImp.addImgforProduct(image);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/admin/formEdit?&id=" + idPr;
    }

    @DeleteMapping("/deleteIMG")
    public String deleteIMG(@RequestParam("url") String url) {
        productServiceImp.deleteImg(url);
        return "success";
    }

    @PostMapping("/updateProduct")
    public String editProduct(@RequestParam("id") String id,
                              @RequestParam("menu") String menu,
                              @RequestParam("discount") int discount,
                              @RequestParam("price") int price,
                              @RequestParam("name") String name,
                              @RequestParam("nsx") String nsx,
                              @RequestParam("hsd") String hsd,
                              @RequestParam("brand") String brand,
                              @RequestParam("mota") String mota,
                              @RequestParam("weight") double weight,
                              @RequestParam("origin") String origin,
                              @RequestParam("inventory") int inventory,
                              @RequestParam("condition") int condition) {

        Detail_Pr detailPr = new Detail_Pr(id, Date.valueOf(nsx), Date.valueOf(hsd), brand, mota, weight, origin, null, inventory, condition);
        Product pr = new Product(id, menu, discount, price, name, detailPr);
        productServiceImp.update(pr);

        return "redirect:/admin/formEdit?&id=" + id;
    }
}
