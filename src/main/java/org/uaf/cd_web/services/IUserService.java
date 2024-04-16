package org.uaf.cd_web.services;

import org.uaf.cd_web.entity.User;

import java.util.List;

public interface IUserService {
    List<User> getListUser();
    long getCountUser();
    User getUserById(String id);
    void createUser(User user);
    void updateUser(byte decentralization, String userId);
}
