package com.tkouleris.contact.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tkouleris.contact.Model.Contact;
import com.tkouleris.contact.dao.ContactRepository;

@RestController
public class ContactController {
	
	@Autowired
	ContactRepository R_Contact;
	
	@GetMapping(value="contacts/all")
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
	public ResponseEntity<Contact> create(@RequestBody Contact contact)
	{	
		Contact newContact = R_Contact.save(contact);
		
		return new ResponseEntity<>(newContact,HttpStatus.CREATED);
	}
	
	@PutMapping(path = "contacts/{contact_id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Contact> update(@PathVariable("contact_id") long contact_id, @RequestBody @Valid Contact contact) throws Exception
	{
		Contact upd_contact = R_Contact.findById(contact_id).orElse(null);
		if(upd_contact == null) return new ResponseEntity<>(contact,HttpStatus.NOT_FOUND);
		
		if(contact.getContact_firstname() != null )upd_contact.setContact_firstname(contact.getContact_firstname());
		if(contact.getContact_lastname() != null )upd_contact.setContact_lastname(contact.getContact_lastname());
		if(contact.getContact_phone() != null )upd_contact.setContact_phone(contact.getContact_phone());
		if(contact.getContact_email() != null )upd_contact.setContact_email(contact.getContact_email());
		
		R_Contact.save(upd_contact);
		
		return new ResponseEntity<>(upd_contact,HttpStatus.OK);
	}
	
	@DeleteMapping(path = "contacts/{contact_id}")
	public ResponseEntity<Contact> delete(@PathVariable("contact_id") long contact_id)
	{
		Contact contact = R_Contact.findById(contact_id).orElse(null);
		
		HttpStatus  StatusCode = HttpStatus.NOT_FOUND;		
		if(contact != null ) {
			R_Contact.delete(contact);
			StatusCode = HttpStatus.OK;
		}
				
		return new ResponseEntity<>(contact,StatusCode);
	}	
	

}
