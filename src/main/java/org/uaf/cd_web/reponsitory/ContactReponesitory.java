package org.uaf.cd_web.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uaf.cd_web.entity.Contact;

public interface ContactReponesitory extends JpaRepository<Contact,String> {
}
