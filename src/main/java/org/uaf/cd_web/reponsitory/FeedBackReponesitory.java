package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.uaf.cd_web.entity.FeedBack;

import java.util.List;

@Repository
public interface FeedBackReponesitory extends JpaRepository<FeedBack, String> {
    List<FeedBack> findByIdPr(String idPr);
}
