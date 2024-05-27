package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uaf.cd_web.entity.Sold_Pr;

import java.util.List;

public interface SoldPrReponesitory extends JpaRepository<Sold_Pr,String> {
    List<Sold_Pr> findAllByIdOrders(String idOrder);
}
