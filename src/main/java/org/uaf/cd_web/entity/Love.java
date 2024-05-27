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
@Table(name="love")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Love implements Serializable {
    @Id
    @Column(name = "ID_PR")
    private String idPr;
    @Column(name = "ID_USER")
    private String iduser;

    @Override
    public String toString() {
        return "Love{" +
                "idPr='" + idPr + '\'' +
                ", iduser='" + iduser + '\'' +
                '}';
    }
}