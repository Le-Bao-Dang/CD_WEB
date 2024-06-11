package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.uaf.cd_web.entity.Discount;

@Repository
public interface DiscountReponesitory extends JpaRepository<Discount, String> {
    Discount getDiscountByCode(String code);
}
