package org.uaf.cd_web.entity;

import jakarta.persistence.*;
import lombok.*;
import org.uaf.cd_web.services.ProductServiceImp;

import java.io.Serializable;


@Entity
@Table(name = "image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Image implements Serializable {

    @ManyToOne()
    @JoinColumn(name = "ID_PR", referencedColumnName = "ID_PR")
    private Product product;
    @Id
    @Column(name = "ID_IMG")
    private String idImg;
    @Column(name = "URL")
    private String url;
    @Column(name = "STATUS")
    private int status;



    public String getAvt() {
        if (status == 0) {
            return url;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Image{" +
                "product=" + product +
                ", idImg='" + idImg + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status +
                '}';
    }
}
