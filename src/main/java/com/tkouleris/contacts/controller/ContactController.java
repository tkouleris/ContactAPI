package com.tkouleris.contacts.controller;

import com.tkouleris.contacts.dto.response.ApiResponse;
import com.tkouleris.contacts.entity.Contact;
import com.tkouleris.contacts.entity.User;
import com.tkouleris.contacts.service.ContactService;
import com.tkouleris.contacts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<Object> getContacts(Authentication authentication){
        User user = this.userService.findByUsername(authentication.getName());
        List<Contact> contacts = this.contactService.all(user);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(contacts);
        apiResponse.setMessage("contacts list");
        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.OK);
    }

    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity<Object> createContact(Authentication auth, @RequestBody Contact contact){
        User loggedInUser = this.userService.findByUsername(auth.getName());
        Contact newContact = this.contactService.create(contact, loggedInUser);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(newContact);
        apiResponse.setMessage("contacts list");
        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.CREATED);
    }

    @PostMapping(path = "/update", produces = "application/json")
    public ResponseEntity<Object> updateContact(@RequestBody Contact contact) throws Exception {
        Contact updatedContact = this.contactService.update(contact);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(updatedContact);
        apiResponse.setMessage("contacts list");
        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.OK);
    }

    @DeleteMapping(path="/delete/{id}", produces = "application/json")
    public ResponseEntity<Object> deleteContact(@PathVariable("id") long id) throws Exception {
        Contact deletedContact = this.contactService.delete(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(deletedContact);
        apiResponse.setMessage("contact deleted");
        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.OK);
    }
}
