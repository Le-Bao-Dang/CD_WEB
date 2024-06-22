package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.uaf.cd_web.entity.Customers;

@Repository
public interface CustomersReponesitory extends JpaRepository<Customers,Integer > {
}
