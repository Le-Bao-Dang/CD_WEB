package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.uaf.cd_web.entity.Product;

import java.util.List;

@Repository
public interface ProductReponesitory extends JpaRepository<Product,String> {
    Product getProductByIdPr(String id);
    @Query("SELECT  p FROM Product p WHERE p.idMenu =:idMenu")
    List<Product> getListProductByKind(String idMenu);
}
