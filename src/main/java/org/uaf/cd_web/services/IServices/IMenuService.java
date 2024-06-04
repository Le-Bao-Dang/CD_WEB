package org.uaf.cd_web.services.IServices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uaf.cd_web.entity.Menu;

public interface IMenuService {
    Menu getMenuById(String idmenu);
}
