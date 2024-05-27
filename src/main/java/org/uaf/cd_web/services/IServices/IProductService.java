package org.uaf.cd_web.services.IServices;

import org.uaf.cd_web.entity.Image;
import org.uaf.cd_web.entity.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface IProductService {
    List<Product> getListProduct();
    List<Product> getListProductByKind(String kind);

    Product listProductById(String idpr);

    public List<Product> getIdMenuPr();


    List<Product> getListProductInPage(String kind, int page);

    List<Product> getListProductInPage(int page);

    int getSize();
    int getSize(String kind);

    String formatTime(LocalDateTime dateTime);

    Product getProductById(String id);
    void add(Product product);

    void addImgforProduct(Image image);

    void update(Product product);
    void delete(String id);
    List<Product> search(String keyword);


    void deleteImg(String url);


    List<Product> listLikeProduct(String idUser);
}
