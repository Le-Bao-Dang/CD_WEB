package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.uaf.cd_web.entity.Sold_Pr;

import java.util.List;

@Repository
public interface SoldPrReponesitory extends JpaRepository<Sold_Pr, String> {
    @Query("SELECT s FROM Sold_Pr s Join  Orders  o On s.idOrders= o.idOrders WHERE (o.status = 2 OR o.status = 3) AND s.idUser =: idUser")
    List<Sold_Pr> historyUser(String idUser);

public interface SoldPrReponesitory extends JpaRepository<Sold_Pr,String> {
    List<Sold_Pr> findAllByIdOrders(String idOrder);
}
