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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USER")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
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
    // Các trạng thái của đơn hàng
    public static final int PENDING_CONFIRMATION = 0; // Chờ xác nhận
    public static final int CONFIRMED = 1;            // Đã xác nhận
    public static final int PREPARING = 2;            // Đang chuẩn bị
    public static final int SHIPPING = 3;             // Đang giao
    public static final int DELIVERED = 4;            // Đã giao
    public static final int FAILED = 5;               // Giao không thành công
    public static final int CANCELLED = 6;            // Đã hủy

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
        switch (status) {
            case CANCELLED:
                return "Đã hủy";
            case FAILED:
                return "Giao không thành công";
            case PREPARING:
                return "Đang chuẩn bị";
            case SHIPPING:
                return "Đang giao";
            case DELIVERED:
                return "Đã giao";
            case PENDING_CONFIRMATION:
                return "Chờ xác nhận";
            case CONFIRMED:
                return "Đã xác nhận";
            default:
                return "Đơn hàng không xác định";
        }

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

    public long getGrandtotal() {
        long sum = getSumOrder();
        sum = discount != null ? sum - discount.getValue() : sum;
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
