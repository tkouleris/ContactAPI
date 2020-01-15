package com.tkouleris.contact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Contact> getAll(){		
		return (List<Contact>) R_Contact.findAll();		
	}
	
	@GetMapping(value="contacts/{contact_id}")
	public Contact getContact(@PathVariable("contact_id") long contact_id)
	{		
		return R_Contact.findById(contact_id).orElse(null);
	}
	
	@PostMapping(path = "contacts/create", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Contact create(@RequestBody Contact contact)
	{	
		return R_Contact.save(contact);
	}

}
