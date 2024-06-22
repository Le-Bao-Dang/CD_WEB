package org.uaf.cd_web.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;
@Entity
@Table(name = "contact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact implements Serializable {
    @Id
    @Column(name = "ID_CONTACT")
    private String idContact;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER",insertable = false,updatable = false)
    private User user;
    @Column(name = "ID_CUSTOMERS")
    private int idCustomer;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "DATETIME")
    private LocalDateTime dateTime;
    @Column(name = "STATUS")
    private int condition;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CUSTOMERS", insertable=false, updatable=false)
    private Customers customers;

    public String checkConditionContact() {
        if (condition == 0) {
            return "Đã xem";

        }
        return "Chưa xem";
    }


}
