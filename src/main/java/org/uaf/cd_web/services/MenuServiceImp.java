package org.uaf.cd_web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uaf.cd_web.entity.Menu;
import org.uaf.cd_web.reponsitory.MenuReponesitory;
import org.uaf.cd_web.services.IServices.IMenuService;

@Service
public class MenuServiceImp implements IMenuService {
    private final MenuReponesitory menuReponesitory;

    @Autowired
    public MenuServiceImp(MenuReponesitory menuReponesitory) {
        this.menuReponesitory = menuReponesitory;
    }

    @Override
    public Menu getMenuById(String idmenu) {
        return menuReponesitory.getReferenceById(idmenu);
    }
}
