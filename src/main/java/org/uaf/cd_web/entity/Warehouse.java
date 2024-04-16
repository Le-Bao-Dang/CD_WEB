package org.uaf.cd_web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;


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



    @Override
    public String toString() {
        return "Warehouse [idShipment=" + idShipment + ", codeWh=" + codeWh + ", dateImportShipment="
                + dateImportShipment + ", nameEmployee=" + nameEmployee + "]";
    }

}
