package org.uaf.cd_web.reponsitory;

import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersReponesitory extends JpaRepository<Order,String> {
}
