package org.uaf.cd_web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "cart")
@Data
public class Cart implements Serializable {
    @Id
    @Column (name = "ID_USER")
    private  String idUser;
    @Column(name="ID_PR")
    private  String idPr;
    @Column(name="AMOUNT")
    private int amout;

    @Override
    public String toString() {
        return "Cart{" +
                "idUser='" + idUser + '\'' +
                ", idPr='" + idPr + '\'' +
                ", amout=" + amout +
                '}';
    }

}
