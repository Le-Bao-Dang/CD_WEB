
package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.uaf.cd_web.entity.Cart;
import org.uaf.cd_web.entity.Love;

import java.util.List;

@Repository
public interface LoveReponesitory extends JpaRepository<Love, Integer> {

    @Modifying
    @Transactional
    @Query("delete from Love where idPr=:idPr and idUser=:idUser")
    void deleteByIdPrAndIdUser(String idPr, String idUser);

    @Query("SELECT l FROM Love l WHERE l.idPr = :idProd AND l.idUser = :idUser")
    List<Love> checkLiked(String idUser, String idProd);

}