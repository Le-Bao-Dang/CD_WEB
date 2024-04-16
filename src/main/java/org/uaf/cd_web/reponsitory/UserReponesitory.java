package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.uaf.cd_web.entity.User;


@Repository
public interface UserReponesitory extends JpaRepository<User, String> {
    @Modifying
    @Query("UPDATE  User u set u.decentralization=:decentralization where u.idUser=:userId")
    void updateUser( byte decentralization, String userId);

    User getUserByIdUser(String id);
}
