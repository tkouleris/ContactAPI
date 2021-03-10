package com.tkouleris.contacts.service;

import com.tkouleris.contacts.dao.IContactsRepository;
import com.tkouleris.contacts.entity.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final IContactsRepository contactsRepository;

    public ContactService(IContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    public List<Contact> all(){
        return (List<Contact>) this.contactsRepository.findAll();
    }


    public Contact create(Contact contact) {
        Contact newContact = new Contact();
        newContact.setFirstname(contact.getFirstname());
        newContact.setLastname(contact.getLastname());
        newContact.setPhone(contact.getPhone());
        return this.contactsRepository.save(newContact);
    }

    public Contact update(Contact contact) throws Exception {
        Contact retrievedContact = this.contactsRepository.findById((long)contact.getId()).orElse(null);
        if(retrievedContact == null){
            throw new Exception("Contact not found");
        }

        if(contact.getPhone() != null){
            retrievedContact.setPhone(contact.getPhone());
        }

        if(contact.getFirstname() != null){
            retrievedContact.setFirstname(contact.getFirstname());
        }

        if(contact.getLastname() != null){
            retrievedContact.setLastname(contact.getLastname());
        }

        return this.contactsRepository.save(retrievedContact);

    }
}
