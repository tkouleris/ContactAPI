package com.tkouleris.contacts.service;

import com.tkouleris.contacts.dao.IContactsRepository;
import com.tkouleris.contacts.entity.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private IContactsRepository contactsRepository;

    public ContactService(IContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    public List<Contact> all(){
        return (List<Contact>) this.contactsRepository.findAll();
    }


}
