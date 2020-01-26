package com.tkouleris.contact.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

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
	public ResponseEntity<Object> getAll(){		
		Iterable<Contact> contacts = R_Contact.findAll();
		
	    Map<String, Object> body = new LinkedHashMap<>();
	    body.put("timestamp", LocalDateTime.now());
	    body.put("message", "Contacts List");
	    body.put("data", contacts);
	    
	    return new ResponseEntity<>(body, HttpStatus.OK);	
	}
	
	@GetMapping(value="contacts/{contact_id}")
	public ResponseEntity<Object> getContact(@PathVariable("contact_id") long contact_id)
	{		
		Contact contact = R_Contact.findById(contact_id).orElse(null);
		
		HttpStatus StatusCode = HttpStatus.OK;
		String msg = "Contact Found";
		if(contact == null ) {
			StatusCode = HttpStatus.NOT_FOUND;
			msg = "Contact not found";
		}
		
	    Map<String, Object> body = new LinkedHashMap<>();
	    body.put("timestamp", LocalDateTime.now());
	    body.put("message", msg);
	    body.put("data", contact);
		
		return new ResponseEntity<>(body, StatusCode);
	}
	
	@PostMapping(path = "contacts/create", consumes = "application/json", produces = "application/json")	
	public ResponseEntity<Object> create(@RequestBody Contact contact)
	{	
		Contact newContact = R_Contact.save(contact);
		
	    Map<String, Object> body = new LinkedHashMap<>();
	    body.put("timestamp", LocalDateTime.now());
	    body.put("message", "Contact created!");
	    body.put("data", newContact);
		
		return new ResponseEntity<>(body,HttpStatus.CREATED);
	}
	
	@PutMapping(path = "contacts/{contact_id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> update(@PathVariable("contact_id") long contact_id, @RequestBody @Valid Contact contact) throws Exception
	{
		Contact upd_contact = R_Contact.findById(contact_id).orElse(null);
		if(upd_contact == null) {
		    Map<String, Object> body = new LinkedHashMap<>();
		    body.put("timestamp", LocalDateTime.now());
		    body.put("message", "Contact not found!");		    
		    
			return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);			
		}
			
		
		if(contact.getContact_firstname() != null )upd_contact.setContact_firstname(contact.getContact_firstname());
		if(contact.getContact_lastname() != null )upd_contact.setContact_lastname(contact.getContact_lastname());
		if(contact.getContact_phone() != null )upd_contact.setContact_phone(contact.getContact_phone());
		if(contact.getContact_email() != null )upd_contact.setContact_email(contact.getContact_email());
		
		R_Contact.save(upd_contact);
		
	    Map<String, Object> body = new LinkedHashMap<>();
	    body.put("timestamp", LocalDateTime.now());
	    body.put("message", "Contact Updated!");
	    body.put("data", upd_contact);
		
		return new ResponseEntity<>(body,HttpStatus.OK);
	}
	
	@DeleteMapping(path = "contacts/{contact_id}")
	public ResponseEntity<Object> delete(@PathVariable("contact_id") long contact_id)
	{
		Contact contact = R_Contact.findById(contact_id).orElse(null);
		
		HttpStatus  StatusCode = HttpStatus.NOT_FOUND;
		String msg = "Contact not found";
		if(contact != null ) {
			R_Contact.delete(contact);
			StatusCode = HttpStatus.OK;
			msg = "Contact Deleted";
		}
		
	    Map<String, Object> body = new LinkedHashMap<>();
	    body.put("timestamp", LocalDateTime.now());
	    body.put("message", msg);
	    body.put("data", contact);
				
		return new ResponseEntity<>(body,StatusCode);
	}	
	

}
