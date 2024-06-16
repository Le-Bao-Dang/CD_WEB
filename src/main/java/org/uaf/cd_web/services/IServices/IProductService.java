package org.uaf.cd_web.services.IServices;

import org.springframework.data.domain.Page;
import org.uaf.cd_web.entity.Detail_Pr;
import org.uaf.cd_web.entity.FeedBack;
import org.uaf.cd_web.entity.Image;
import org.uaf.cd_web.entity.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface IProductService {
    List<Product> getListProduct();

    List<Product> getListProductByKind(String kind);

    Product listProductById(String idpr);

    public List<Product> getIdMenuPr();


    Page<Product> getListProductInPage(String kind, int page);

    List<Product> getListProductInPage(int page);

    int getSize();

    int getSize(String kind);

    String formatTime(LocalDateTime dateTime);

    Product getProductById(String id);

    void add(Product product);

    void addImgforProduct(Image image);

    void update(Product product);

    void delete(String id);


    List<Product> searchAutocomplete(String keyword);

    void deleteImg(String url);

    List<Product> listLikeProduct(String idUser);

    List<Product> getListProductHostSale();

    int getStopPr();

    Page<Product> listAll(int page, String sortField, String sortDir, String keyword);

    void updateInventoryCT_PR(String idProduct, int i);
    List<Product> getListPrDiscount();

    List<Detail_Pr> getSingleProduct(String idPr);

    List<Image> findByIdPr(String idPro);

    List<FeedBack> getFeedBack(String idPro);

    List<Product> getRelatedProducts(String idMenu);

    List<FeedBack> getFeedBackInPage(String idProd, int page);
    Page<Product> listAllPr(int page, String sortField, String sortDir, String kind);
}
