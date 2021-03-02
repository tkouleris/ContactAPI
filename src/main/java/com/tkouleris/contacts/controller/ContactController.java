package com.tkouleris.contacts.controller;

import com.tkouleris.contacts.entity.Contact;
import com.tkouleris.contacts.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<Object> getContacts(){
        List<Contact> contacts = this.contactService.all();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }
}
