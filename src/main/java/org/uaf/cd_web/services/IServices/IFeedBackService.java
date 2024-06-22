package org.uaf.cd_web.services.IServices;

import java.sql.Date;

public interface IFeedBackService {
    long getCountFeedBack();

    void insertFeedBack(String idPr, String idUser, int scorestar, String text, Date date);
}
