
package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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

    @Query("SELECT COUNT(c.idPr) FROM Cart c WHERE c.idUser = :idUser ")
    List<Integer> getCountCart(String idUser);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Cart c WHERE c.idUser= :idUser AND c.idPr = :idPr")
    boolean checkExit(String idUser, String idPr);

    @Modifying
    @Transactional
    @Query("UPDATE Cart c SET c.amount = c.amount + :amount WHERE c.idPr = :idPr AND c.idUser = :idUser")
    void updateToCart(String idUser, String idPr, int amount);

//    @Query("DELETE FROM Love l WHERE l.idPr = :idPr AND l.iduser = :idUser")
//    void deleteFromLove(String idUser, String idPr);

//    void deleteByIdPrAndIdUser(String idPr, String idUser);

}
