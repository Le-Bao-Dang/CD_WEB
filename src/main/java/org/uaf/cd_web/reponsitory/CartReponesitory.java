
package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.uaf.cd_web.entity.Cart;
import org.uaf.cd_web.entity.Product;

import java.util.List;
@Repository
public interface CartReponesitory extends JpaRepository<Cart, String> {
    @Query("select c, p from Cart c  join  Product p on c.idPr =p.idPr join  Image  i on  i.idPr= p.idPr where i.status=0 and  c.idUser=:idUser")
    List<Cart> getListCart(String idUser);

    @Query("select p from Cart c  join  Product p on c.idPr =p.idPr  where  c.idUser=:idUser")
    Product getProductInCart(String idUser);

    @Query("select c from Cart c JOIN FETCH c.product where   c.idUser=:idUser")
    List<Cart> findAllByIdUser(String idUser);
}