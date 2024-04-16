package org.uaf.cd_web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uaf.cd_web.entity.Product;
import org.uaf.cd_web.reponsitory.ProductReponesitory;

import java.util.List;
@Service
public class ProductServiceImp implements  IProductService {
    @Autowired
    ProductReponesitory productReponesitory;
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

    public static void main(String[] args) {
        ProductServiceImp p= new ProductServiceImp();
        System.out.println(p.getListProduct());
    }
}
