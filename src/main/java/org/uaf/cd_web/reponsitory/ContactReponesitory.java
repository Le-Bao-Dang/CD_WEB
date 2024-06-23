package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.uaf.cd_web.entity.Contact;
import java.util.List;
@Repository
public interface ContactReponesitory extends JpaRepository<Contact,String> {
    public List<Contact> findByIdContact(String idContact);

    @Query("SELECT MAX(CAST(REPLACE(c.idContact, 'cont', '') AS INTEGER )) FROM Contact c")
    int getMaxId();
}
