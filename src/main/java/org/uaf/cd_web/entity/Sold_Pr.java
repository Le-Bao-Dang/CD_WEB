package org.uaf.cd_web.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sold_pr")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Sold_Pr implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "ID_PR", insertable = false, updatable = false)
    private Product product;
    @Column(name = "TIME_SOLD")
    private LocalDateTime timeSold;
    @Column(name = "AMOUNT")
    private int amount;
    @ManyToOne
    @JoinColumn(name = "ID_ORDERS")
    private Orders orders;
    @Column(name = "PRICE_HERE")
    private int priceHere;

    public Sold_Pr( Product product, LocalDateTime timeSold, int amount, Orders orders, int priceHere) {
        this.product = product;
        this.timeSold = timeSold;
        this.amount = amount;
        this.orders = orders;
        this.priceHere = priceHere;
    }


    public String getTotalPrice() {
        DecimalFormat dec = new DecimalFormat("#,###");
        return dec.format(this.amount * this.priceHere).replace(',', '.');
    }


    public String formatTime() {
        return timeSold.getDayOfMonth() + "-" + timeSold.getMonthValue() + "-" + timeSold.getYear() + " "
                + timeSold.getHour() + ":" + timeSold.getMinute();
    }



}
