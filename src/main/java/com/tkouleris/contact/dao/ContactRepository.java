package com.tkouleris.contact.dao;

import org.springframework.data.repository.CrudRepository;

import com.tkouleris.contact.Model.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long>{

}
