package com.tkouleris.contacts.dao;

import com.tkouleris.contacts.entity.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContactsRepository extends CrudRepository<Contact, Long> {
    @Query("select c from Contact c where c.contact_user =?1 order by c.lastname desc, c.firstname desc")
    List<Contact> findAllByUserID(Long userid);
}
