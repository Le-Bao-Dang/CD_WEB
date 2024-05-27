package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.uaf.cd_web.entity.Image;

import java.util.List;
@Repository
public interface ImageReponesitory extends JpaRepository<Image,String> {
    List<Image> getImageByIdPr(String idPr);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO image (ID_PR, ID_IMG, URL, status) VALUES (:idPr, :idImg, :url, :status)", nativeQuery = true)
    void savePr(String idPr, String idImg,String url, int status);

    void deleteImageByUrl(String url);
}
