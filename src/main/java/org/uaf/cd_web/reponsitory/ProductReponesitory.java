package org.uaf.cd_web.reponsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import org.uaf.cd_web.entity.Product;

import java.util.List;

@Repository

public interface ProductReponesitory extends JpaRepository<Product, String>, PagingAndSortingRepository<Product, String> {
    @Query("SELECT p, l, i FROM Product p JOIN Love l ON l.idPr = p.idPr JOIN Image i ON p.idPr = i.idPr WHERE i.status = 0 AND l.iduser=:idUser")
    List<Product> listLikeProduct(String idUser);

    @Query("select p from Product  p where p.idPr=:idpr ")
    Product listProductById(String idpr);

    Product getProductByIdPr(String id);

    @Query("SELECT  p FROM Product p WHERE p.idMenu =:idMenu")
    List<Product> getListProductByKind(String idMenu);


    @Query("SELECT p FROM Product p join Menu m on p.idMenu=m.idMenu where m.paMenu=:idMenu")
    Page<Product> getProductByPaMenu(String idMenu, Pageable pageable);

    @Query("select count(c) from Detail_Pr c where c.conditionPR = 1 ")
    Integer getStopPr();

    @Query("SELECT p, MAX(i.url) as URL, MAX(c.brand) as BRAND, SUM(s.amount) as saled FROM Product p JOIN Image i ON p.idPr = i.idPr " +
            "JOIN Detail_Pr c ON c.idPr = p.idPr " +
            "JOIN Sold_Pr s ON s.idPr = p.idPr WHERE i.status = 0 " +
            "GROUP BY p,c ORDER BY saled DESC")

    public List<Product> getListHostSalePr();
}
