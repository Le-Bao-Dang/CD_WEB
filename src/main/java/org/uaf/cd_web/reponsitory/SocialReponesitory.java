package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.uaf.cd_web.entity.Social;

import java.util.List;

@Repository
public interface SocialReponesitory extends JpaRepository<Social, Integer> {

    List<Social> findAll();


}
