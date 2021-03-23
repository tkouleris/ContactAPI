package com.tkouleris.contacts.weblayer;


import com.tkouleris.contacts.dao.IContactsRepository;
import com.tkouleris.contacts.entity.Contact;
import com.tkouleris.contacts.entity.User;
import com.tkouleris.contacts.service.CustomUserDetails;
import com.tkouleris.contacts.util.JwtUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static junit.framework.TestCase.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ContactsTest {

    @Autowired
    private IContactsRepository contactsRepository;

    @Autowired
    private Helper helper;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String token;

    @BeforeEach
    public void cleanup(){
        this.helper.cleanAll();
    }

    @Test
    public void returnAllContacts_StatusOK()
    {
        // given
        String token = this.helper.getToken();
        // when
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity entity = new HttpEntity(headers);
//        ResponseEntity<Object> response =  testRestTemplate.getForEntity("/api/contacts/all",null, Object.class);
        ResponseEntity<Object> response = testRestTemplate.exchange("/api/contacts/all", HttpMethod.GET, entity, (Class<Object>) null, Object.class);
        // then
        assertEquals( 0, contactsRepository.count());
        assertEquals(response.getStatusCode().value(), 200);
    }

    @Test
    public void aNewContactIsSaved_whenContactIsValid_receiveCreated(){
        // given
        Contact newContact = createValidContact();
        String token = this.helper.getToken();
        // when
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Contact> entity = new HttpEntity<>(newContact,headers);
//        ResponseEntity<Object> response =  testRestTemplate.postForEntity("/api/contacts/create",newContact, Object.class);
        ResponseEntity<Object> response = testRestTemplate.exchange("/api/contacts/create", HttpMethod.POST, entity, (Class<Object>) null, Object.class);

        // then
        assertEquals( 1, contactsRepository.count());
        assertEquals(response.getStatusCode().value(), 201);
    }

    @Test
    public void updatesAContact_whenContactIsValid_receiveOK(){
        // given
        Contact contact = createValidContact();
        Contact savedContact = contactsRepository.save(contact);
        savedContact.setPhone("777");
        savedContact.setFirstname("Alvin");
        savedContact.setLastname("Chipmunk");
        String token = this.helper.getToken();

        // when
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Contact> entity = new HttpEntity<>(savedContact,headers);
//        ResponseEntity<Object> response =  testRestTemplate.postForEntity("/api/contacts/update",savedContact, Object.class);
        ResponseEntity<Object> response = testRestTemplate.exchange("/api/contacts/update", HttpMethod.POST, entity, (Class<Object>) null, Object.class);
        Contact retrievedContact = contactsRepository.findById(savedContact.getId()).orElse(null);

        // then
        assertEquals(200, response.getStatusCode().value());
        assertEquals("777",retrievedContact.getPhone());
        assertEquals("Alvin",retrievedContact.getFirstname());
        assertEquals("Chipmunk",retrievedContact.getLastname());
    }

    @Test
    public void deleteAContact_whenContactExists_receiveOK(){
        // given
        Contact contact = createValidContact();
        Contact savedContact = contactsRepository.save(contact);
        String token = this.helper.getToken();

        // when
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Contact> entity = new HttpEntity<>(savedContact,headers);
        ResponseEntity<Object> response =  testRestTemplate.exchange("/api/contacts/delete/"+contact.getId()
                , HttpMethod.DELETE
                , entity
                ,Object.class);
        List<Contact> contacts = (List<Contact>) contactsRepository.findAll();
        // then
        assertEquals(200, response.getStatusCode().value());
        assertEquals(0,contacts.size());
    }

    // helpers
    private Contact createValidContact() {
        Contact newContact = new Contact();
        newContact.setFirstname("firstname");
        newContact.setLastname("lastname");
        newContact.setPhone("666");
        return newContact;
    }
}
