package org.uaf.cd_web.entity;

import java.io.Serializable;
import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "detail_wh")
@Data
public class Detail_WH implements Serializable {
    @Id
    @Column(name = "ID_SHIPMENT")
    private String idShipment;
    @Column(name = "ID_PR")
    private String idPR;
    @Column(name = "PRICE_IMPORT")
    private int priceImport;
    @Column(name = "AMOUT_PR")
    private int amountPr;
    @Column(name = "WEIGHT_PR")
    private int weightPr;

    public String getIdShipment() {
        return idShipment;
    }

    public void setIdShipment(String idShipment) {
        this.idShipment = idShipment;
    }

    public String getIdPR() {
        return idPR;
    }

    public void setIdPR(String idPR) {
        this.idPR = idPR;
    }

    public int getPriceImport() {
        return priceImport;
    }

    public void setPriceImport(int priceImport) {
        this.priceImport = priceImport;
    }

    public int getAmountPr() {
        return amountPr;
    }

    public void setAmountPr(int amountPr) {
        this.amountPr = amountPr;
    }

    public int getWeightPr() {
        return weightPr;
    }

    public void setWeightPr(int weightPr) {
        this.weightPr = weightPr;
    }

    @Override
    public String toString() {
        return "Detail_WH [idShipment=" + idShipment + ", idPR=" + idPR + ", priceImport=" + priceImport + ", amountPr="
                + amountPr + ", weightPr=" + weightPr + "]";
    }

}
