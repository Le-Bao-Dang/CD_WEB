package org.uaf.cd_web.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Table(name = "image")
@Getter
@Setter
public class Image implements Serializable {

    @Column(name = "ID_PR", insertable = false, updatable = false)
    private String idPr;
    @Id
    @Column(name = "ID_IMG")
    private String idImg;
    @Column(name = "URL")
    private String url;
    @Column(name = "status")
    private int status;

    @ManyToOne()
    @JoinColumn(name = "ID_PR", referencedColumnName = "ID_PR")
    private Product product;

    public Image(String idPr, String idImg, String url, int status) {
        this.idPr = idPr;
        this.idImg = idImg;
        this.url = url;
        this.status = status;
    }
    public Image() {

    }

    @Override
    public String toString() {
        return "Image [idPr=" + idPr + ", idImg=" + idImg + ", url=" + url + ", status=" + status + "]";
    }

}
