package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.uaf.cd_web.entity.User;

@Repository
public interface UserReponesitory extends JpaRepository<User, String> {

}
