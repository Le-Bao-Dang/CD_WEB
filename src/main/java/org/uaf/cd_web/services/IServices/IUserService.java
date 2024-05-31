package org.uaf.cd_web.services.IServices;

import org.uaf.cd_web.entity.User;

import java.sql.Date;
import java.util.List;

public interface IUserService {
    List<User> getListUser();
    long getCountUser();
    List<User> getUserById(String id);
    void createUser(User user);

    void createUser(String name, String phone, String email, String passw);

    void updateUser(byte decentralization, String userId);

    void updateAccount(String iduser, String address, String passw, String name, String phone, String email, String birthday, Date datesignup, boolean sex);
}
