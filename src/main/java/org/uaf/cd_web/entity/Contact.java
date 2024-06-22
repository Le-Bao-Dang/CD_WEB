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
    @ManyToOne
    @JoinColumn(name = "ID_USER", nullable = false)
    private User user;
    @OneToOne
    @JoinColumn(name = "ID_CUSTOMERS", referencedColumnName = "ID_CUSTOMERS")
    private Customers customers;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "DATETIME")
    private LocalDateTime dateTime;
    @Column(name = "STATUS")
    private int condition;

    public String checkConditionContact() {
        if (condition == 0) {
            return "Đã xem";

        }
        return "Chưa xem";
    }


}
