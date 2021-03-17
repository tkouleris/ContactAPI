package com.tkouleris.contacts.weblayer;

import com.tkouleris.contacts.dao.IContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Helper {

    @Autowired
    IContactsRepository contactsRepository;

    public void cleanAll()
    {
        this.contactsRepository.deleteAll();
    }

}
