package com.tkouleris.contacts.service;

import com.tkouleris.contacts.dao.IContactsRepository;
import com.tkouleris.contacts.dao.IUserRepository;
import com.tkouleris.contacts.entity.Contact;
import com.tkouleris.contacts.entity.User;
import com.tkouleris.contacts.exception.contact.ContactNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final IContactsRepository contactsRepository;

    public ContactService(IContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    public List<Contact> all(User user){
        return (List<Contact>) this.contactsRepository.findAllByUserID(user.getId());
    }


    public Contact create(Contact contact, User user) {
        Contact newContact = new Contact();
        newContact.setFirstname(contact.getFirstname());
        newContact.setLastname(contact.getLastname());
        newContact.setPhone(contact.getPhone());
        newContact.setContact_user(user);
        return this.contactsRepository.save(newContact);
    }

    public Contact update(Contact contact) throws Exception {
        Contact retrievedContact = this.contactsRepository.findById((long)contact.getId()).orElse(null);
        if(retrievedContact == null){
            throw new ContactNotFoundException("Contact not found");
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

    public Contact delete(long id) throws Exception {
        Contact contact = this.contactsRepository.findById(id).orElse(null);
        if(contact == null){
            throw new ContactNotFoundException("Contact not found");
        }
        this.contactsRepository.delete(contact);
        return contact;
    }
}
