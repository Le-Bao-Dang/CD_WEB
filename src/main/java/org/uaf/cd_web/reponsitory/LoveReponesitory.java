
package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.uaf.cd_web.entity.Cart;
import org.uaf.cd_web.entity.Love;

@Repository
public interface LoveReponesitory extends JpaRepository<Love, String> {

    @Modifying
    @Transactional
    @Query("delete from Love where idPr=:idPr and idUser=:idUser")
    void deleteByIdPrAndIdUser(String idPr, String idUser);
}