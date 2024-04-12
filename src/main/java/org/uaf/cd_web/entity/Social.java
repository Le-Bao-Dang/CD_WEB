package org.uaf.cd_web.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "social")
@Data
public class Social implements  Serializable {
    @Id
    @Column(name="CONDITION")
    private int condition;
    @Column(name="ID_USER")
    private  String idUser;
    @Column(name="ID_ACCOUNT")
    private String IdAccount;

    @Override
    public String toString() {
        return "Social{" +
                "condition=" + condition +
                ", idUser='" + idUser + '\'' +
                ", IdAccount='" + IdAccount + '\'' +
                '}';
    }
}
