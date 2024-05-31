package org.uaf.cd_web.services;

import org.springframework.stereotype.Service;
import org.uaf.cd_web.entity.Contact;
import org.uaf.cd_web.reponsitory.ContactReponesitory;
import org.uaf.cd_web.services.IServices.IContactService;

import java.util.List;
@Service
public class ContactServiceImp implements IContactService {
    public final ContactReponesitory contactReponesitory;

    public ContactServiceImp(ContactReponesitory contactReponesitory) {
        this.contactReponesitory = contactReponesitory;
    }
    @Override
    public int getSumContact(){
        return (int)contactReponesitory.count();
    }
    public List<Contact> getListContact(){
        return contactReponesitory.findAll();
    }
}
