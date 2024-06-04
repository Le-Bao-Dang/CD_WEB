package org.uaf.cd_web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Love> listLove;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Cart> listcart;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Orders> listOrders;




    public String checkDecentralization( ){
        if(this.decentralization==0) return "User";
        if(this.decentralization==2) return "Admin";
        if(this.decentralization==-1) return "Ẩn";
        return "Employee";
    }



    public String printCtAccount(String vl){
        if (this.birthday==null || this.phone==null|| this.email==null  || this.address==null ) {
            return "";
        }
        return vl;
    }
}
