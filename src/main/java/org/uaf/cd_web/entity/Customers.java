package org.uaf.cd_web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Table(name="customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customers implements Serializable {
    @Id
    @Column(name="ID_CUSTOMERS")
    private int idCustomers;
    @Column(name = "NAME")
    private String name;
    @Column(name= "PHONE")
    private String phone;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "EMAIL")
    private String email;


    @Override
    public String toString() {
        return "Customers{" +
                "idCustomers=" + idCustomers +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
