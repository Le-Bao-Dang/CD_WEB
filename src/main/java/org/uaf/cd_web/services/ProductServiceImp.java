package org.uaf.cd_web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uaf.cd_web.entity.Image;
import org.uaf.cd_web.entity.Product;
import org.uaf.cd_web.reponsitory.ImageReponesitory;
import org.uaf.cd_web.reponsitory.ProductReponesitory;
import org.uaf.cd_web.reponsitory.UserReponesitory;
import org.uaf.cd_web.services.IServices.IProductService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImp implements IProductService {
    private final ProductReponesitory productReponesitory;
    private final ImageReponesitory imageReponesitory;
    private final UserReponesitory userReponesitory;

    @Autowired
    public ProductServiceImp(ProductReponesitory productReponesitory, ImageReponesitory imageReponesitory, UserReponesitory userReponesitory) {
        this.productReponesitory = productReponesitory;
        this.imageReponesitory = imageReponesitory;
        this.userReponesitory = userReponesitory;
    }

    @Override
    public List<Product> getListProduct() {
        List<Product> listPr = productReponesitory.findAll();
        List<Image> listImg;
        for (Product p : listPr) {
            listImg = imageReponesitory.getImageByIdPr(p.getIdPr());
            p.setImage(listImg);
        }
        return listPr;
    }

    @Override
    public List<Product> listLoveProduct(String idUser) {
        return productReponesitory.listLoveProduct(idUser);
    }

    @Override
    public List<Product> searchProduct(String keyword) {
        return productReponesitory.searchProduct(keyword);
    }

    @Override
    public List<Product> getListProductByKind(String kind) {
        List<Product> list = new ArrayList<>();
        list = productReponesitory.getListProductByKind(kind);
        if (list == null) {
            list = getListProduct();
        }
        for (Product p : list) {
            p.setImage(imageReponesitory.getImageByIdPr(p.getIdPr()));
        }
        return list;
    }

    @Override
    public List<Product> getIdMenuPr() {
        return List.of();
    }

    @Override
    public List<Product> getListProductInPage(String kind, int page) { // phân trang
        List<Product> list = new ArrayList<>();
        List<Product> prByKind = getListProductByKind(kind);
        int start = (page - 1) * 15 < 0 ? 0 : (page - 1) * 15;
        int end = page <= prByKind.size() / 15 ? page * 15 : prByKind.size() - ((page - 1) * 15) + start;
        for (int i = start; i < end; i++) {
            list.add(prByKind.get(i));
        }
        return list;
    }

    @Override
    public List<Product> getListProductInPage(int page) { // phân trang
        List<Product> list = new ArrayList<>();
        List<Product> prByKind = getListProduct();
        int start = (page - 1) * 15 < 0 ? 0 : (page - 1) * 15;
        int end = page <= prByKind.size() / 15 ? page * 15 : prByKind.size() - ((page - 1) * 15) + start;
        for (int i = start; i < end; i++) {
            list.add(prByKind.get(i));
        }
        return list;
    }

    @Override
    public int getSize() {
        return (int) productReponesitory.count();
    }

    @Override
    public int getSize(String kind) {
        int size = getListProductByKind(kind).size();
        return size;
    }

    @Override
    public String formatTime(LocalDateTime dateTime) {
        return dateTime.getDayOfMonth() + "-" + dateTime.getMonthValue() + "-" + dateTime.getYear() + " "
                + dateTime.getHour() + ":" + dateTime.getMinute();
    }

    @Override
    public Product getProductById(String id) {
        Product product = productReponesitory.getProductByIdPr(id);
        product.setImage(imageReponesitory.getImageByIdPr(product.getIdPr()));
        return product;
    }

    @Override
    public void add(Product product) {
        Product p = product;
        int index = getSize() + 1;
        p.setIdPr("prod" + index);
        product.getDetailPr().setIdPr("prod" + index);
        productReponesitory.save(p);
    }

    @Override
    @Transactional
    public void addImgforProduct(Image image) {
        Image i = image;
        i.setIdPr(image.getIdPr());
        i.setIdImg(image.getIdImg());
        // imageReponesitory.save(i);
        imageReponesitory.savePr(i.getIdPr(), i.getIdImg(), i.getUrl(), i.getStatus());
    }

    public List<Product> getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productReponesitory.findAll(pageable);
        return productPage.getContent();
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(String id) {
    }

    @Override
    public Page<Product> findAll(Pageable page) {
        return productReponesitory.findAll(page);
    }



}
