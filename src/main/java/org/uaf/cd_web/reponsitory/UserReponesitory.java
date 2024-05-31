package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.uaf.cd_web.entity.User;

import java.time.LocalDateTime;
import java.util.List;


@Repository
@EnableJpaRepositories
public interface UserReponesitory extends JpaRepository<User, String> {
    @Modifying
    @Query("UPDATE  User u set u.decentralization=:decentralization where u.idUser=:userId")
    void updateUser(byte decentralization, String userId);

    @Query("select u from User u  where u.email=:username or u.phone=:username")
    User checkLogin(String username);

    @Query("select u from User u  where u.email=:email or u.phone=:phone")
    List<User> checkUserExit(String email, String phone);

    User getUserByIdUser(String id);

    @Query("SELECT u from User u where month(u.dateSignup)=: date")
    List<User> getNewbie(LocalDateTime date);

}
