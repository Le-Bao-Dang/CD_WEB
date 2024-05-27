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
    @Modifying

    @Query("SELECT p.idPr, p.idMenu , p.discount, p.price, p.namePr, i.url FROM Love l JOIN Product p ON l.idPr = p.idPr JOIN Image i ON p.idPr = i.idPr WHERE i.status = 0 AND l.iduser= :idUser")
    List<Product> listLoveProduct(String idUser);

    Product getProductByIdPr(String id);
    
    @Query("SELECT  p FROM Product p WHERE p.idMenu =:idMenu")
    List<Product> getListProductByKind(String idMenu);


    @Query("SELECT  p FROM Product p  WHERE  p.namePr LIKE %:keyword%")
     List<Product> searchProduct(@Param("keyword") String keyword);

     Page<Product> findAll(Pageable pageable);
}
