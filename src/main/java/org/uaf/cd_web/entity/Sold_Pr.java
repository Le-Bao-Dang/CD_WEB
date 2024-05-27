package org.uaf.cd_web.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sold_pr")
@Getter
@Setter
public class Sold_Pr implements Serializable {
    @Id
    @Column(name = "ID_PR")
    private String idPr;
    @Column(name = "ID_USER")
    private String idUser;
    @Column(name = "TIME_SOLD")
    private LocalDateTime timeSold;
    @Column(name = "AMOUNT")
    private int amount;
    @Column(name = "ID_ORDERS")
    private String idOrders;
    @Column(name = "PRICE_HERE")
    private int priceHere;


    @OneToMany(mappedBy = "sold_pr", cascade = CascadeType.ALL)
    private List<Orders> orders;

    @Override
    public String toString() {
        return "Sold_Pr{" +
                "idPr='" + idPr + '\'' +
                ", idUser='" + idUser + '\'' +
                ", timeSold=" + timeSold +
                ", amount=" + amount +
                ", idOrders='" + idOrders + '\'' +
                ", priceHere=" + priceHere +
                '}';
    }
}
