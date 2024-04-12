
package org.uaf.cd_web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "product")
@Data
public class Product implements Serializable {
    @Id
    @Column(name = "ID_PR")
    private String idPr;
    @Column(name = "ID_MENU")
    private String idMenu;
    @Column(name = "DISCOUNT")
    private String discount;
    @Column(name = "PRICE")
    private int price;
    @Column(name = "NAME_PR")
    private String namePr;

    @Override
    public String toString() {
        return "Product [idPr=" + idPr + ", idMenu=" + idMenu + ", discount=" + discount + ", price=" + price
                + ", namePr=" + namePr + "]";
    }

}
