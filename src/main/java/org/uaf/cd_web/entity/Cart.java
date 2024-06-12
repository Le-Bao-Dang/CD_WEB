package org.uaf.cd_web.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @JsonManagedReference
    private User user;

    public Cart(String idUser, String idPr, Integer amount) {
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
