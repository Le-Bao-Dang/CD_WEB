package org.uaf.cd_web.services.IServices;

import org.uaf.cd_web.entity.User;

import java.util.List;

public interface IUserService {
    List<User> getListUser();
    long getCountUser();
    User getUserById(String id);
    void createUser(User user);

    void createUser(String name, String phone, String email, String passw);

    void updateUser(byte decentralization, String userId);
}
