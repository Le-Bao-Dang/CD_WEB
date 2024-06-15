package org.uaf.cd_web.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "social")
@Data
public class Social implements  Serializable {
    @Column(name="ID_USER")
    private  String idUser;
    @Column(name="STATUS")
    private int status;
    @Id
    @Column(name="ID_ACCOUNT")
    private String idAccount;

    @OneToOne()
    @JoinColumn(name = "ID_USER",insertable=false, updatable=false)
    private User user;

    @Override
    public String toString() {
        return "Social{" +
                "status=" + status +
                ", idUser='" + idUser + '\'' +
                ", IdAccount='" + idAccount + '\'' +
                '}';
    }
}
