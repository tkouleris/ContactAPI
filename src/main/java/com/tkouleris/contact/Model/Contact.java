package com.tkouleris.contact.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Contact {
	
	@Id
	private long contact_id;
	
	private String contact_firstname;
	private String contact_lastname;
	private String contact_email;
	private String contact_phone;
	
	public long getContact_id() {
		return contact_id;
	}
	public void setContact_id(long contact_id) {
		this.contact_id = contact_id;
	}
	public String getContact_firstname() {
		return contact_firstname;
	}
	public void setContact_firstname(String contact_firstname) {
		this.contact_firstname = contact_firstname;
	}
	public String getContact_lastname() {
		return contact_lastname;
	}
	public void setContact_lastname(String contact_lastname) {
		this.contact_lastname = contact_lastname;
	}
	public String getContact_email() {
		return contact_email;
	}
	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}
	public String getContact_phone() {
		return contact_phone;
	}
	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}
	
	

}
