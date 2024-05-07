package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.uaf.cd_web.entity.Product;
@Repository
public interface ProductReponesitory extends JpaRepository<Product, String> {


}
