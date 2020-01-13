package com.tkouleris.contact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tkouleris.contact.Model.Contact;
import com.tkouleris.contact.dao.ContactRepository;

@RestController
public class ContactController {
	
	@Autowired
	ContactRepository R_Contact;
	
	@GetMapping(value="/all")
	@ResponseBody
	public List<Contact> getAll(){		
		return (List<Contact>) R_Contact.findAll();		
	}
	

}
