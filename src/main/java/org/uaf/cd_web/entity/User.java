package org.uaf.cd_web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {
    @Id
    @Column(name="ID_USER")
    private String idUser;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "PASSW")
    private String passw;
    @Column(name = "NAME_USER")
    private String nameUser;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "BIRTHDAY")
    private Date birthday;
    @Column(name = "DATE_SIGNUP")
    private Date dateSignup;
    @Column( name = "SEX")
    private Boolean sex;
    @Column(name = "Decentralization")
    private int decentralization;

    //lấy thông tin của tk gg
//    @Column(name="idgg")
//    private String idgg;
//    @Column(name="date_sigup")
//    private Date date_sigup_gg;
//    @Column(name="name")
//    private  String name;
//    @Column(name="id")
//    private String id;
    public String checkDecentralization( ){
        if(this.decentralization==0) return "User";
        if(this.decentralization==2) return "Admin";
        if(this.decentralization==-1) return "Ẩn";
        return "Employee";
    }
    @Override
    public String toString() {
        return "User{" +
                "idUser='" + idUser + '\'' +
                ", address='" + address + '\'' +
                ", passw='" + passw + '\'' +
                ", nameUser='" + nameUser + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", dateSignup=" + dateSignup +
                ", sex=" + sex +
                ", decentralization=" + decentralization +
//                ", idgg='" + idgg + '\'' +
//                ", date_sigup_gg=" + date_sigup_gg +
//                ", name='" + name + '\'' +
//                ", id='" + id + '\'' +
                '}';
    }
    public String printCtAccount(String vl){
        if (this.birthday==null || this.phone==null|| this.email==null  || this.address==null ) {
            return "";
        }
        return vl;
    }
}
