package org.uaf.cd_web.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "menu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {
    @Id
    @Column(name = "ID_MENU")
    private String idMenu;
    @Column(name = "NAME_MENU")
    private String nameMenu;
    @Column(name = "PA_MENU")
    private String paMenu;


    @Override
    public String toString() {
        return "Menu [idMenu=" + idMenu + ", nameMenu=" + nameMenu + ", apaenu=" + paMenu + "]";
    }


}
