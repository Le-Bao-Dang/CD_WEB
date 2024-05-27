
package org.uaf.cd_web.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @Column(name = "ID_PR")
    private String idPr;
    @Column(name = "ID_MENU")
    private String idMenu;
    @Column(name = "DISCOUNT")
    private int discount;
    @Column(name = "PRICE")
    private int price;
    @Column(name = "NAME_PR")
    private String namePr;
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Detail_Pr detailPr;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> image;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Sold_Pr> prList;

    public Product(String idPr, String idMenu, int discount, int price, String namePr, Detail_Pr detailPr) {
        this.idPr = idPr;
        this.idMenu = idMenu;
        this.discount = discount;
        this.price = price;
        this.namePr = namePr;
        this.detailPr = detailPr;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idPr='" + idPr + '\'' +
                ", idMenu='" + idMenu + '\'' +
                ", discount='" + discount + '\'' +
                ", price=" + price +
                ", namePr='" + namePr + '\'' +
                ", detailPr=" + detailPr +
                ", image=" + image.size() +
                '}';
    }
}
