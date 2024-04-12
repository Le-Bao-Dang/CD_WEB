package org.uaf.cd_web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "ct_pr")
@Data
public class CT_PR implements Serializable {
    @Id
    @Column(name = "ID_PR")
    private String idPr;
    @Column(name = "NSX")
    private Date nsx;
    @Column(name = "HSD")
    private Date hsd;
    @Column(name = "BRAND")
    private String brand;
    @Column(name = "DESCRIBE")
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

    public String checkConditionPr() {
        if (conditionPR == 0) {
            return "Đang bán";
        }

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

    public String getIdPr() {
        return idPr;
    }

    public void setIdPr(String idPr) {
        this.idPr = idPr;
    }

    public Date getNsx() {
        return nsx;
    }

    public void setNsx(Date nsx) {
        this.nsx = nsx;
    }

    public Date getHsd() {
        return hsd;
    }

    public void setHsd(Date hsd) {
        this.hsd = hsd;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Date getDate_import_pr() {
        return date_import_pr;
    }

    public void setDate_import_pr(Date date_import_pr) {
        this.date_import_pr = date_import_pr;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getConditionPR() {
        return conditionPR;
    }

    public void setConditionPR(int conditionPR) {
        this.conditionPR = conditionPR;
    }

}
