package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.uaf.cd_web.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.uaf.cd_web.entity.Product;

import java.util.List;

@Repository
public interface ProductReponesitory extends JpaRepository<Product, String> {
    @Modifying

    @Query("SELECT p.idPr, p.idMenu , p.discount, p.price, p.namePr, i.url FROM Love l JOIN Product p ON l.idPr = p.idPr JOIN Image i ON p.idPr = i.idPr WHERE i.condition = 0 AND l.iduser= :idUser")
    List<Product> listLikeProduct(String idUser);

    @Query("select p from Product  p where p.idPr=:idpr ")
    Product listProductById(String idpr);

public interface ProductReponesitory extends JpaRepository<Product,String> {
    Product getProductByIdPr(String id);
    @Query("SELECT  p FROM Product p WHERE p.idMenu =:idMenu")
    List<Product> getListProductByKind(String idMenu);
}
