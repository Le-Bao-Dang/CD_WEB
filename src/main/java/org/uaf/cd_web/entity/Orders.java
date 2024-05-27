package org.uaf.cd_web.entity;

import jakarta.persistence.*;
import lombok.*;
import org.uaf.cd_web.components.Format;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders implements Serializable {
    @Id
    @Column(name = "ID_ORDERS")
    private String idOrders;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "TIME_ORDERS")
    private LocalDateTime timeOrders;
    @Column(name = "TIME_PICKUP")
    private LocalDateTime timePickup;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "CONDITION")
    private int condition;


    @ManyToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Sold_Pr> prList;

    public String checkCondition() {
        if (condition == -2) return "Đã hủy";
        if (condition == -1) return "giao không thành công";
        if (condition == 0) return "Đang chuẩn bị";
        if (condition == 1) return "Đang giao";
        if (condition == 2) return "Đã giao";
        if (condition == 3) return "Chờ xác nhận";
        if (condition == 4) return "Đã xác nhận";
        return "Đơn hàng không xác định";

    }

    public int getAmountPr() {
        int sum = 0;
        for (Sold_Pr pr : prList) {
            sum += pr.getAmount();
        }
        return sum;
    }

    public long getSumOrder() {

        long sum = 0;
        for (Sold_Pr pr : prList) {
            sum += (pr.getAmount() * pr.getPriceHere());
        }
        return sum;
    }

    public String getToStringSumOrder() {
        long sum = getSumOrder();
        DecimalFormat dc = new DecimalFormat("#,###");
        return dc.format(sum).replace(',', '.');
    }
    public String getDate(){
        Format f = new Format();
       return f.formatDateTimeNow(this.getTimeOrders());
    }

    @Override
    public String toString() {
        return "Orders{" +
                "idOrders='" + idOrders + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", timeOrders=" + timeOrders +
                ", timePickup=" + timePickup +
                ", note='" + note + '\'' +
                ", condition=" + condition +
                '}';
    }
}
