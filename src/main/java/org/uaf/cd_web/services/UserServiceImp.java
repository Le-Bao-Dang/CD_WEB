package org.uaf.cd_web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.reponsitory.UserReponesitory;

import java.util.List;

@Service
public class UserServiceImp implements IUserService {
    @Autowired
    UserReponesitory userReponesitory;
    private BCryptPasswordEncoder passwordEncoder;

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
    public User getUserById(String id) {
        return userReponesitory.getUserByIdUser(id);
    }

    @Override
    public void createUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassw());
        User u = user;
        u.setIdUser("user" + getCountUser());
        u.setPassw(encodedPassword);
        userReponesitory.save(u);
    }

    @Override
    public void updateUser(byte decentralization, String userId) {
        User u = userReponesitory.getUserByIdUser(userId);
        u.setDecentralization(decentralization);
        userReponesitory.save(u);
    }

    public static void main(String[] args) {
        UserServiceImp u = new UserServiceImp();
        System.out.println(u.getListUser());
    }
}
