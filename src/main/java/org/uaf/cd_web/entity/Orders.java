package org.uaf.cd_web.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data

public class Orders implements Serializable {
    @Id
    @Column(name="ID_ORDERS")
    private  String idOrders;
    @Column(name="NAME")
    private String name;
    @Column(name="PHONE")
    private  String phone;
    @Column (name="ADDRESS")
    private String address;
    @Column (name="TIME_ORDERS")
    private LocalDateTime timeOrders;
    @Column (name="TIME_PICKUP")
    private  LocalDateTime timePickup;
    @Column (name="NOTE")
    private  String note;
    @Column (name="CONDITION")
    private  int condition;

    public  String checkCondition(){
        if(condition==0) return "Đang chuẩn bị";
        if(condition==1) return  "Đang giao";
        if(condition==2) return "Đã giao";
        return "Giao không thành công";
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
