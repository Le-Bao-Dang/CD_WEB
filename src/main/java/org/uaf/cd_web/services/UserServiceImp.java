package org.uaf.cd_web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uaf.cd_web.entity.User;
import org.uaf.cd_web.reponsitory.UserReponesitory;
import org.uaf.cd_web.services.IServices.IUserService;

import java.util.ArrayList;
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
    @Transactional
    public void updateUser(byte decentralization, String userId) {
//        User u = userReponesitory.getUserByIdUser(userId);
//        u.setDecentralization(decentralization);
        userReponesitory.updateUser(decentralization, userId);
    }

    @Transactional
    public List<User> searchUser(String keyword) {
        List<User> list = new ArrayList<>();
        String keywordUp = keyword.toUpperCase();
        List<User> listUser = getListUser();
        if(keyword.equals("")){
            list =listUser;
        }
        for (User user : listUser) {
            if (keywordUp.contains(user.getNameUser().toUpperCase()) || keywordUp.contains(user.getEmail().toUpperCase()) || keywordUp.contains(user.getPhone())) {
                list.add(user);
            }
        }
        return list;
    }
}
