package org.uaf.cd_web.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
    private String idUser;



    @ManyToOne()
    @JoinColumn(name = "ID_PR", referencedColumnName = "ID_PR", insertable = false, updatable = false)
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER",insertable=false, updatable=false)
    @JsonManagedReference
    private User user;

    @Override
    public String toString() {
        return "Love{" +
                "idPr='" + idPr + '\'' +
                ", iduser='" + idUser + '\'' +
                '}';
    }

    public Love(String idPr, String idUser) {
        this.idPr = idPr;
        this.idUser = idUser;
    }
}