package com.tkouleris.contact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tkouleris.contact.Model.Contact;
import com.tkouleris.contact.dao.ContactRepository;

@RestController
public class ContactController {
	
	@Autowired
	ContactRepository R_Contact;
	
	@GetMapping(value="contacts/all")
	@ResponseBody
	public ResponseEntity<Iterable<Contact>> getAll(){		
		Iterable<Contact> contacts = R_Contact.findAll();
	    return new ResponseEntity<>(contacts , HttpStatus.OK);	
	}
	
	@GetMapping(value="contacts/{contact_id}")
	public ResponseEntity<Contact> getContact(@PathVariable("contact_id") long contact_id)
	{		
		Contact contact = R_Contact.findById(contact_id).orElse(null);
		
		HttpStatus StatusCode = HttpStatus.OK;
		if(contact == null ) StatusCode = HttpStatus.NOT_FOUND; 
		
		return new ResponseEntity<>(contact, StatusCode);
	}
	
	@PostMapping(path = "contacts/create", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Contact create(@RequestBody Contact contact)
	{	
		return R_Contact.save(contact);
	}
	
	@DeleteMapping(path = "contacts/{contact_id}")
	@ResponseBody
	public Contact delete(@PathVariable("contact_id") long contact_id)
	{
		Contact contact = R_Contact.findById(contact_id).orElse(null);
		
		R_Contact.delete(contact);
		
		return contact;
	}	
}
