package org.uaf.cd_web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uaf.cd_web.entity.Detail_Pr;
import org.uaf.cd_web.entity.FeedBack;
import org.uaf.cd_web.entity.Image;
import org.uaf.cd_web.entity.Product;
import org.uaf.cd_web.reponsitory.*;
import org.uaf.cd_web.services.IServices.IProductService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImp implements IProductService {
    private final ProductReponesitory productReponesitory;
    private final ImageReponesitory imageReponesitory;
    private final SoldPrReponesitory soldPrReponesitory;
    private final DetailProductReponesitory detailProductReponesitory;
    private final FeedBackReponesitory feedBackReponesitory;

    @Autowired
    public ProductServiceImp(ProductReponesitory productReponesitory, ImageReponesitory imageReponesitory, SoldPrReponesitory soldPrReponesitory, DetailProductReponesitory detailProductReponesitory, FeedBackReponesitory feedBackReponesitory) {
        this.productReponesitory = productReponesitory;
        this.imageReponesitory = imageReponesitory;
        this.soldPrReponesitory = soldPrReponesitory;
        this.detailProductReponesitory = detailProductReponesitory;
        this.feedBackReponesitory = feedBackReponesitory;
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
    public List<Product> listLikeProduct(String idUser) {
        return productReponesitory.listLikeProduct(idUser);
    }

    @Override
    public Product listProductById(String idpr) {
        return productReponesitory.listProductById(idpr);
    }

    @Override
    public List<Product> getIdMenuPr() {
        return null;
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
    public Page<Product> getListProductInPage(String kind, int page) { // phân trang
        kind = "m" + kind;
        Pageable pageable = PageRequest.of(page, 15);
        return productReponesitory.getProductByPaMenu(kind, pageable);
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

    // format thời gian ngày tháng năm giờ phút
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
        i.setUrl(image.getUrl());
        imageReponesitory.save(i);
//        imageReponesitory.savePr(i.getIdPr(), i.getIdImg(), i.getUrl(), i.getStatus());
    }

    public List<Product> getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productReponesitory.findAll(pageable);
        return productPage.getContent();
        // imageReponesitory.save(i);
        // imageReponesitory.savePr(i.getIdPr(), i.getIdImg(), i.getUrl(),
        // i.getStatus());
    }

    @Override
    public void update(Product product) {
        Product pr = productReponesitory.getProductByIdPr(product.getIdPr());
        pr.setPrice(product.getPrice());
        pr.setMenu(product.getMenu());
        pr.setDiscount(product.getDiscount());
        pr.setNamePr(product.getNamePr());
        Detail_Pr detail_pr = pr.getDetailPr();
        detail_pr.setNsx(product.getDetailPr().getNsx());
        detail_pr.setHsd(product.getDetailPr().getHsd());
        detail_pr.setBrand(product.getDetailPr().getBrand());
        detail_pr.setDescribe(product.getDetailPr().getDescribe());
        detail_pr.setWeight(product.getDetailPr().getWeight());
        detail_pr.setOrigin(product.getDetailPr().getOrigin());
        detail_pr.setInventory(product.getDetailPr().getInventory());
        detail_pr.setConditionPR(product.getDetailPr().getConditionPR());
        pr.setDetailPr(detail_pr);
        productReponesitory.save(pr);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<Product> searchAutocomplete(String keyword) {
        return productReponesitory.searchAutocomplete(keyword);
    }

    @Override
    @Transactional
    public void deleteImg(String url) {
        imageReponesitory.deleteImageByUrl(url);
    }

    @Override
    public List<Product> getListProductHostSale() {
        return productReponesitory.getListHostSalePr();
    }

    @Override
    public int getStopPr() {
        Integer result = productReponesitory.getStopPr();
        return result != null ? result : 0;
    }

    @Override
    public Page<Product> listAll(int page, String sortField, String sortDir, String keyword) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        if (!keyword.equals("")) {
            return productReponesitory.findProduct(keyword, pageable);
        }
        return productReponesitory.findAll(pageable);
    }

    @Override
    public void updateInventoryCT_PR(String idProduct, int inventoryOrder) {
        productReponesitory.updateInventory(idProduct,inventoryOrder);


    }
    public List<Product> getListPrDiscount() {
        return productReponesitory.getListPrDiscount();
    }

    @Override
    public List<Detail_Pr> getSingleProduct(String idPr) {
        return detailProductReponesitory.getSingleProduct(idPr);
    }

    @Override
    public List<Image> findByIdPr(String idPro) {
        return imageReponesitory.findByIdPr(idPro);
    }
@Override
    public List<FeedBack> getFeedBack(String idPro) {
        return feedBackReponesitory.findByIdPr(idPro);

    }
    @Override
    public List<Product> getRelatedProducts(String idMenu){
        return productReponesitory.findRelatedProductsByIdMenu(idMenu);
    }

}

