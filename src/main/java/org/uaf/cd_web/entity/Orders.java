package org.uaf.cd_web.entity;

import jakarta.persistence.*;
import lombok.*;
import org.aspectj.apache.bcel.classfile.Code;
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
@ToString(exclude = {"prList", "user", "discount"})
public class Orders implements Serializable {
    @Id
    @Column(name = "ID_ORDERS")
    private String idOrders;
    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User user;
    @ManyToOne
    @JoinColumn(name = "ID_CODE")
    private Discount discount;
    @ManyToOne
    @JoinColumn(name = "ID_CUSTOMERS")
    private Customers customers;
    @Column(name = "TIME_ORDERS")
    private LocalDateTime timeOrders;
    @Column(name = "TIME_PICKUP")
    private LocalDateTime timePickup;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "STATUS")
    private int status;
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Sold_Pr> prList;

    public Orders(String idOrder, User user, Discount discount, Customers customers, LocalDateTime timeNow, LocalDateTime timePickup, String note, int status) {
        this.idOrders = idOrder;
        this.user = user;
        this.discount = discount;
        this.customers = customers;
        this.timeOrders = timeNow;
        this.timePickup = timePickup;
        this.note = note;
        this.status = status;
    }

    public String checkstatus() {
        if (status == -2) return "Đã hủy";
        if (status == -1) return "Giao không thành công";
        if (status == 0) return "Đang chuẩn bị";
        if (status == 1) return "Đang giao";
        if (status == 2) return "Đã giao";
        if (status == 3) return "Chờ xác nhận";
        if (status == 4) return "Đã xác nhận";
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

    public String getDate() {
        Format f = new Format();
        return f.formatDateTimeNow(this.getTimeOrders());
    }

    public Object getUser() {
        if (this.user == null) return this.customers;
        return this.user;
    }


}
