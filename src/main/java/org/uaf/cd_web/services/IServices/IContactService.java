package org.uaf.cd_web.services.IServices;
import org.uaf.cd_web.entity.User;
public interface IContactService {
    int getSumContact();
    public int getSumCustomers();
    public void addContact( User user, String content, String nameuser, String phone, String email);
}
