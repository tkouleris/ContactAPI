package com.tkouleris.contacts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/contacts")
public class ContactController {

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<Object> getContacts()
    {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
