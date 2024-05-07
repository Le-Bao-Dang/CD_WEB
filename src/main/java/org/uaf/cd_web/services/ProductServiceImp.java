package org.uaf.cd_web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uaf.cd_web.entity.Product;
import org.uaf.cd_web.reponsitory.ProductReponesitory;
import org.uaf.cd_web.services.IServices.IProductService;

import java.util.List;
@Service
public class ProductServiceImp implements IProductService {
    private final ProductReponesitory productReponesitory;
@Autowired
    public ProductServiceImp(ProductReponesitory productReponesitory) {
        this.productReponesitory = productReponesitory;
    }

    @Override
    public  List<Product> getListProduct() {
        List<Product>  listPr= productReponesitory.findAll();
        return listPr;
    }

    @Override
    public List<Product> getIdMenuPr() {
        return null;
    }

    @Override
    public List<Product> getIdParentMenuPr() {
        return null;
    }




}
