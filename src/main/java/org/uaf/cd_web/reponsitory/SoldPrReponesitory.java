package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.uaf.cd_web.entity.Sold_Pr;

import java.util.List;

@Repository
public interface SoldPrReponesitory extends JpaRepository<Sold_Pr, Integer> {
    @Query("Select s from Sold_Pr s join Orders o on s.orders.idOrders= o.idOrders join Product p on s.product.idPr= p.idPr where (o.status= -1 or o.status= -2 or o.status= 2) and o.user.idUser= :idUser ORDER by o.timePickup desc")
    List<Sold_Pr> historyUser(String idUser);

    @Query("Select s, o, p from Sold_Pr s join Orders o on s.orders.idOrders= o.idOrders join Product p on s.product.idPr= p.idPr where (o.status= 0 or o.status= 1 or o.status= 3 or o.status=4 ) and o.user.idUser= :idUser ORDER by o.timePickup desc")
    List<Sold_Pr> getManagerOrderUser(String idUser);


//    List<Sold_Pr> findAllByIdOrderById(String idOrder);
}
