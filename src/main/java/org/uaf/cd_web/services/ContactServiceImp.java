package org.uaf.cd_web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uaf.cd_web.entity.Contact;
import org.uaf.cd_web.reponsitory.ContactReponesitory;
import org.uaf.cd_web.reponsitory.CustomersReponesitory;

import java.time.LocalDateTime;

import org.uaf.cd_web.services.IServices.IContactService;
import org.uaf.cd_web.entity.Customers;
import org.uaf.cd_web.entity.User;
import java.util.List;

@Service
public class ContactServiceImp implements IContactService {
    public final ContactReponesitory contactReponesitory;
    public final CustomersReponesitory customersReponesitory;

    @Autowired
    public ContactServiceImp(ContactReponesitory contactReponesitory, CustomersReponesitory customersReponesitory) {
        this.contactReponesitory = contactReponesitory;
        this.customersReponesitory = customersReponesitory;
    }

    @Override
    public int getSumContact() {
        return (int) contactReponesitory.count()+1;
    }

    @Override
    public int getSumCustomers() {
        return (int) customersReponesitory.count();
    }

    public List<Contact> getListContact() {
        return contactReponesitory.findAll();
    }

    @Override
    public void addContact(User user, String content, String nameuser, String phone, String email) {
        Customers c = new Customers();
        c.setIdCustomers(getSumCustomers() + 1);
        c.setName(nameuser);
        c.setPhone(phone);
        c.setEmail(email);
        customersReponesitory.save(c);
        Contact contact = new Contact();
        contact.setIdContact("cont" + getSumContact());
        contact.setUser(user);
        contact.setIdCustomer(c.getIdCustomers());
        contact.setContent(content);
        contact.setDateTime(LocalDateTime.now());
        contact.setCondition(1);
        contactReponesitory.save(contact);
    }

    public List<Contact> viewContact(String idContact) {
        return contactReponesitory.findByIdContact(idContact);
    }

    public void seenContact(String idContact, int condition) {
        Contact contact = contactReponesitory.findByIdContact(idContact).get(0);
            contact.setCondition(condition);
            contactReponesitory.save(contact);

    }

}
