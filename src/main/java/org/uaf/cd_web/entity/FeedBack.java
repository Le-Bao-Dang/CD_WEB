package org.uaf.cd_web.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="feed_back")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FeedBack implements Serializable {
    @Id
    @Column(name = "ID_PR")
    private String idPr;
    @Column(name = "ID_USER")
    private String idUser;
    @Column(name = "SCORESTAR")
    private int scorestar;
    @Column(name = "TEXT")
    private String text;
    @Column(name = "DATE")
    private Date date;



    @ManyToOne()
    @JoinColumn(name = "ID_PR", referencedColumnName = "ID_PR", insertable = false, updatable = false)
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER",insertable=false, updatable=false)
    @JsonManagedReference
    private User user;



}