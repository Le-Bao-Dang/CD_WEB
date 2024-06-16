package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.uaf.cd_web.entity.Menu;
import org.uaf.cd_web.entity.Product;

import java.util.List;

@Repository
public interface MenuReponesitory extends JpaRepository<Menu, String> {
    Menu findByNameMenu(String name);
}
