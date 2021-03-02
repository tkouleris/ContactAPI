package com.tkouleris.contacts.weblayer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tkouleris.contacts.entity.Contact;
import com.tkouleris.contacts.service.ContactService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
public class ContactsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ContactService contactService;

    @Test
    public void contactsListLoadsTest() throws Exception {
        mockMvc.perform(get("/api/contacts/all"))
                .andExpect(status().isOk());

        verify(contactService, times(1)).all();
    }

    @Test
    public void aNewContactIsSaved() throws Exception {
        Contact newContact = new Contact();
        newContact.setFirstname("firstname");
        newContact.setLastname("lastname");
        newContact.setPhone("666");
        mockMvc.perform(post("/api/contacts/create")
                .content(asJsonString(newContact))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(contactService, times(1)).create(any(Contact.class));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
