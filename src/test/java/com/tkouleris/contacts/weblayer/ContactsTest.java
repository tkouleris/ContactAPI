package com.tkouleris.contacts.weblayer;


import com.tkouleris.contacts.dao.IContactsRepository;
import com.tkouleris.contacts.entity.Contact;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static junit.framework.TestCase.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ContactsTest {

    @Autowired
    private IContactsRepository contactsRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void cleanup(){
        contactsRepository.deleteAll();
    }

    @Test
    public void returnAllContactsStatusOK()
    {
        // given

        // when
        ResponseEntity<Object> response =  testRestTemplate.getForEntity("/api/contacts/all",null, Object.class);

        // then
        assertEquals( 0, contactsRepository.count());
        assertEquals(response.getStatusCode().value(), 200);
    }

    @Test
    public void aNewContactIsSaved(){
        // given
        Contact newContact = createValidContact();

        // when
        ResponseEntity<Object> response =  testRestTemplate.postForEntity("/api/contacts/create",newContact, Object.class);

        // then
        assertEquals( 1, contactsRepository.count());
        assertEquals(response.getStatusCode().value(), 201);
    }

    @Test
    public void updatesAContact(){
        // given
        Contact contact = createValidContact();
        Contact savedContact = contactsRepository.save(contact);
        savedContact.setPhone("777");
        savedContact.setFirstname("Alvin");
        savedContact.setLastname("Chipmunk");

        // when
        ResponseEntity<Object> response =  testRestTemplate.postForEntity("/api/contacts/update",savedContact, Object.class);
        Contact retrievedContact = contactsRepository.findById(savedContact.getId()).orElse(null);

        // then
        assertEquals(200, response.getStatusCode().value());
        assertEquals("777",retrievedContact.getPhone());
        assertEquals("Alvin",retrievedContact.getFirstname());
        assertEquals("Chipmunk",retrievedContact.getLastname());
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
