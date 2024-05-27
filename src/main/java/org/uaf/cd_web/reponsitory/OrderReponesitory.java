
package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.uaf.cd_web.entity.Orders;

@Repository
public interface OrderReponesitory extends JpaRepository<Orders, String>, PagingAndSortingRepository<Orders, String> {
    @Query("select s.idPr from Sold_Pr s where s.idOrders =: idOder")
    String[] getListIdPr(String idOder);
}
