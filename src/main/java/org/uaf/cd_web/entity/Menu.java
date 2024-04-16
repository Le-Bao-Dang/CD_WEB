package org.uaf.cd_web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "menu")
@Data
public class Menu implements Serializable {
    @Id
    @Column(name = "ID_MENU")
    private String idMenu;
    @Column(name = "NAME_MENU")
    private String nameMenu;
    @Column(name = "PA_MENU")
    private String apaenu;

    @Override
    public String toString() {
        return "Menu [idMenu=" + idMenu + ", nameMenu=" + nameMenu + ", apaenu=" + apaenu + "]";
    }

    public String getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
    }

    public String getNameMenu() {
        return nameMenu;
    }

    public void setNameMenu(String nameMenu) {
        this.nameMenu = nameMenu;
    }

    public String getApaenu() {
        return apaenu;
    }

    public void setApaenu(String apaenu) {
        this.apaenu = apaenu;
    }

}
