
package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.uaf.cd_web.entity.Orders;
import java.util.List;
@Repository
public interface OrderReponesitory extends JpaRepository<Orders, String>, PagingAndSortingRepository<Orders, String> {
    @Query("select s.idPr from Sold_Pr s where s.idOrders =: idOder")
    String[] getListIdPr(String idOder);
    @Query("SELECT ord.idOrders,ord.status, ord.address,ord.note,ord.phone,ord.name, p,s FROM Orders ord JOIN Sold_Pr s on ord.idOrders= s.idOrders JOIN Product p on s.idPr= p.idPr  WHERE (ord.status =0 or ord.status=1) and s.idUser=:idUser")
    List<Orders> getManageOrders(String idUser);
}
