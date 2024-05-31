package org.uaf.cd_web.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@Data
public class Cart implements Serializable {

    @Column (name = "ID_USER")
    private  String idUser;
    @Id
    @Column(name="ID_PR")
    private  String idPr;
    @Column(name="AMOUNT")
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PR", insertable=false, updatable=false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "ID_USER", insertable = false, updatable = false)
    private User user;

    public Cart() {
    }

    public Cart(String idUser, String idPr, int amount, Product product, User user) {
        this.idUser = idUser;
        this.idPr = idPr;
        this.amount = amount;
        this.product = product;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "idUser='" + idUser + '\'' +
                ", idPr='" + idPr + '\'' +
                ", amount=" + amount +
                '}';
    }

}
