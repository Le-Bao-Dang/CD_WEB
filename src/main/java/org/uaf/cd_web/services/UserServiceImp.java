package org.uaf.cd_web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.reponsitory.UserReponesitory;
import org.uaf.cd_web.services.IServices.IUserService;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImp implements IUserService {
    private final UserReponesitory userReponesitory;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserReponesitory userReponesitory) {
        this.userReponesitory = userReponesitory;
    }

    @Override
    public List<User> getListUser() {
        List<User> listUsers = userReponesitory.findAll();
        return listUsers;
    }

    @Override
    public long getCountUser() {
        return userReponesitory.count();
    }


    @Override
    public List<User> getUserById(String id) {
        return userReponesitory.getUserByIdUser(id);
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void createUser(String name, String phone, String email, String passw) {
        String importDate = LocalDateTime.now().getYear() + "-" + LocalDateTime.now().getMonthValue() + "-" + LocalDateTime.now().getDayOfMonth();
        User u = new User();
        u.setIdUser("user" + getCountUser() + 1);
        u.setNameUser(name);
        u.setDateSignup(Date.valueOf(importDate));
        u.setEmail(email);
        u.setPhone(phone);
        u.setDecentralization(0);
        u.setPassw(passw);
        userReponesitory.save(u);
    }

    @Override
    @Transactional
    public void updateUser(byte decentralization, String userId) {
        userReponesitory.updateUser(decentralization, userId);
    }

    @Override
    @Transactional
    public List<User> searchUser(String keyword) {
        if (keyword.equals("")) return userReponesitory.findAll();
        return userReponesitory.findUser(keyword);
    }

    public User checkLogin(String username) {
        return userReponesitory.checkLogin(username);
    }

    public boolean checkUserExit(String email, String phone) {
        List<User> list = userReponesitory.checkUserExit(email, phone);
        for (User u : list) {
            if (email.equals(u.getEmail()) || phone.equals(u.getPhone())) return true;
        }
        return false;
    }

    @Override
    public void updateAccount(String iduser, String address, String passw, String name, String phone, String email, String birthday, Date datesignup, boolean sex) {
        User u = new User();
        u.setIdUser(iduser);
        u.setAddress(address);
        u.setNameUser(name);
        u.setEmail(email);
        u.setPhone(phone);
        u.setDecentralization(0);
        u.setPassw(passw);
        u.setBirthday(Date.valueOf(birthday));
        u.setSex(sex);
        u.setDecentralization(0);
        u.setDateSignup(datesignup);
        System.out.println(u);
        userReponesitory.save(u);

    }

    public String getEncryptPassUser(String idUser) {
        User user = userReponesitory.findById(idUser).orElse(null);
        return (user != null) ? user.getPassw() : null;
    }

    public Date getDateSignup(String idUser) {
        User user = userReponesitory.findById(idUser).orElse(null);
        return (user != null) ? user.getDateSignup() : null;
    }

    @Override
    public List<User> getNewbie() {
        return userReponesitory.getNewbie(LocalDateTime.now().getMonthValue());
    }

    @Override
    public User getUserByIdUser(String idUser) {
        return userReponesitory.findUserByIdUser(idUser);
    }
}
