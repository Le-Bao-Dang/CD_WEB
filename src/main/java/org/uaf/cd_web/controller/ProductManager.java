package org.uaf.cd_web.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.uaf.cd_web.entity.Detail_Pr;
import org.uaf.cd_web.entity.Image;
import org.uaf.cd_web.entity.Product;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.services.ProductServiceImp;

import java.io.File;
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
        model.addAttribute("listProduct", list);
        model.addAttribute("page", pages);
        model.addAttribute("count", count);
        return "manager_product";
    }

    @PostMapping("/addProduct")
    public String add(@RequestParam("menu") String menu,
                      @RequestParam("discount") String discount,
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

        User user = (User) session.getAttribute("auth");
//        if (user == null) {
//            return "/";
//        }

        // Thực hiện lưu sản phẩm
        int index = productServiceImp.getListProduct().size() + 1;
        Detail_Pr detailPr = new Detail_Pr("", Date.valueOf(nsx), Date.valueOf(hsd), brand, mota, weight, origin, Date.valueOf(LocalDate.now()), inventory, 0);
        Product product = new Product("", menu, discount, price, name, detailPr, null);
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
                System.out.println(idImg);
                System.out.println(image);
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
}
