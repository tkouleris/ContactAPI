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
        ResponseEntity<Object> response =  testRestTemplate.getForEntity("/api/contacts/all",null, Object.class);
        assertEquals( 0, contactsRepository.count());
        assertEquals(response.getStatusCode().value(), 200);
    }

    @Test
    public void aNewContactIsSaved() throws Exception {
        Contact newContact = createValidContact();

        ResponseEntity<Object> response =  testRestTemplate.postForEntity("/api/contacts/create",newContact, Object.class);
        assertEquals( 1, contactsRepository.count());
        assertEquals(response.getStatusCode().value(), 201);
    }

    private Contact createValidContact() {
        Contact newContact = new Contact();
        newContact.setFirstname("firstname");
        newContact.setLastname("lastname");
        newContact.setPhone("666");
        return newContact;
    }
}
