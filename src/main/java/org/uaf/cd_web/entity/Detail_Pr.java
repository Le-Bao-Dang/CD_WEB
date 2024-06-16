package org.uaf.cd_web.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "ct_pr")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Detail_Pr implements Serializable {
    @Id
    @Column(name = "ID_PR")
    private String idPr;
    @Column(name = "NSX")
    private Date nsx;
    @Column(name = "HSD")
    private Date hsd;
    @Column(name = "BRAND")
    private String brand;
    @Column(name = "`DESCRIBE`")
    private String describe;
    @Column(name = "WEIGHT")
    private double weight;
    @Column(name = "ORIGIN")
    private String origin;
    @Column(name = "DATE_IMPORT_PR")
    private Date date_import_pr; 
    @Column(name = "INVENTORY")
    private int inventory;
    @Column(name = "CONDITION_PR")
    private int conditionPR;

    @OneToOne()
    @MapsId
    @JoinColumn(name = "ID_PR",referencedColumnName = "ID_PR")
    private Product product;

    public Detail_Pr(String idPr, Date nsx, Date hsd, String brand, String describe, double weight, String origin, Date date_import_pr, int inventory, int conditionPR) {
        this.idPr = idPr;
        this.nsx = nsx;
        this.hsd = hsd;
        this.brand = brand;
        this.describe = describe;
        this.weight = weight;
        this.origin = origin;
        this.date_import_pr = date_import_pr;
        this.inventory = inventory;
        this.conditionPR = conditionPR;
    }

    public String checkConditionPr() {
        if (conditionPR == 0) {
            return "Đang bán";
        }
        if(conditionPR == 1) return "Sản phẩm mới";
        if(conditionPR == 2) return "Sản phẩm nổi bật";
        return "Ngừng bán";

    }

    @Override
    public String toString() {
        return "CT_PR{" +
                "idPr='" + idPr + '\'' +
                ", nsx=" + nsx +
                ", hsd=" + hsd +
                ", brand='" + brand + '\'' +
                ", describe='" + describe + '\'' +
                ", weight=" + weight +
                ", origin='" + origin + '\'' +
                ", date_import_pr=" + date_import_pr +
                ", inventory=" + inventory +
                ", conditionPR=" + conditionPR +
                '}';
    }

}
