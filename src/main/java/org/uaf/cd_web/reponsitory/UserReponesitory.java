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

    @Query("select u from User u  where u.idUser=:id")
    List<User> getUserByIdUser(String id);

    User findUserByIdUser(String id);

    @Query("SELECT u from User u where month(u.dateSignup)=:month")
    List<User> getNewbie(int month);

    @Query("SELECT u from  User u where u.nameUser like %?1% or u.email like %?1% or u.email like %?1%")
    List<User> findUser(String keyword);

    List<User> findAll();

    @Query("SELECT u from  User u join  Social s on s.idUser=u.idUser where s.idAccount= :idAccount")
    User getUserByIdAccount(String idAccount);

    @Query("SELECT u from User u where u.decentralization<>2")
    List<User> getListEmployee();

    @Query("SELECT MAX(CAST(REPLACE(u.idUser, 'user', '') AS INTEGER )) FROM User u")
    int getMaxID();


}
