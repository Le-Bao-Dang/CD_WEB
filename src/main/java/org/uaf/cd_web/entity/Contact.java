package org.uaf.cd_web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "contact")
@Data
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

    public String getIdContact() {
        return idContact;
    }

    public void setIdContact(String idContact) {
        this.idContact = idContact;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Contact [idContact=" + idContact + ", idUser=" + idUser + ", content=" + content + ", dateTime="
                + dateTime + ", nameUser=" + nameUser + ", email=" + email + ", phone=" + phone + ", condition="
                + condition + "]";
    }

}
