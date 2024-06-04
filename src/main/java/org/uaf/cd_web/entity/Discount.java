package org.uaf.cd_web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "discount")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Discount {
    @Id
    private String ID_CODE;
    @Column(name = "SOLUONG")
    private int soluong;
    @Column(name = "CODE")
    private String code;
    @Column(name = "VALUE")
    private int value;
    @Column(name = "TYPE")
    private boolean type;
    @Column(name = "MINIMUM")
    private int minium;

}
