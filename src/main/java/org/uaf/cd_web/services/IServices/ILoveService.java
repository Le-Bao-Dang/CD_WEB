package org.uaf.cd_web.services.IServices;

public interface ILoveService {
    long getCount();

    void insertLove(String idUser, String idPr);

    boolean checkLiked(String idUser, String idPr);

}
