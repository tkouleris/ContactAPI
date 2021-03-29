package com.tkouleris.contacts.entity;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

@Entity
public class Contact {
    @Id
    @GeneratedValue
    private long Id;

    private String firstname;

    private String lastname;

    private String phone;

    @ManyToOne
    private User contact_user;

    public User getContact_user() {
        return contact_user;
    }

    public void setContact_user(User contact_user) {
        this.contact_user = contact_user;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }
}
