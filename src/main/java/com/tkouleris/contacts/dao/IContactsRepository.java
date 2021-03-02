package com.tkouleris.contacts.dao;

import com.tkouleris.contacts.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContactsRepository extends CrudRepository<Contact, Long> {
}
