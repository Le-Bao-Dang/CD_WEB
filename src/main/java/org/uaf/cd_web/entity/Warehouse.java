package org.uaf.cd_web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlID;

@Entity
@Table(name = "warehouse")
@Data
public class Warehouse implements Serializable {
    @Id
    @Column(name = "ID_SHIPMENT")
    private String idShipment;
    @Column(name = "CODE_WH")
    private String codeWh;
    @Column(name = "DATE_IMPORT_SHIPMENT")
    private Date dateImportShipment;
    @Column(name = "NAME_EMPLOYEE")
    private String nameEmployee;

    public String getIdShipment() {
        return idShipment;
    }

    public void setIdShipment(String idShipment) {
        this.idShipment = idShipment;
    }

    public String getCodeWh() {
        return codeWh;
    }

    public void setCodeWh(String codeWh) {
        this.codeWh = codeWh;
    }

    public Date getDateImportShipment() {
        return dateImportShipment;
    }

    public void setDateImportShipment(Date dateImportShipment) {
        this.dateImportShipment = dateImportShipment;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    @Override
    public String toString() {
        return "Warehouse [idShipment=" + idShipment + ", codeWh=" + codeWh + ", dateImportShipment="
                + dateImportShipment + ", nameEmployee=" + nameEmployee + "]";
    }

}
