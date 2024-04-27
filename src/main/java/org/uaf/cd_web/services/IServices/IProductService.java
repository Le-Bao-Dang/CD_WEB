package org.uaf.cd_web.services.IServices;

import org.uaf.cd_web.entity.Product;

import java.util.List;

public interface IProductService {
    public List<Product> getListProduct();

    public List<Product> getIdMenuPr();

    public List<Product> getIdParentMenuPr();

}
