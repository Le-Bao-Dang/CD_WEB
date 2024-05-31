package org.uaf.cd_web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "contact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact implements Serializable {
    @Id
    @Column(name = "ID_CONTACT")
    private String idContact;
    @Column(name = "ID_USER")
    private String idUser;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "DATETIME")
    private LocalDateTime dateTime;
    @Column(name = "NAMEUSER")
    private String nameUser;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "CONDITION")
    private int condition;

    public String checkConditionContact() {
        if (condition == 0) {
            return "Đã xem";

        }
        return "Chưa xem";
    }

    @Override
    public String toString() {
        return "Contact [idContact=" + idContact + ", idUser=" + idUser + ", content=" + content + ", dateTime="
                + dateTime + ", nameUser=" + nameUser + ", email=" + email + ", phone=" + phone + ", condition="
                + condition + "]";
    }

}
