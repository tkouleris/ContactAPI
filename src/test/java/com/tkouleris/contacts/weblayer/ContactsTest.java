package com.tkouleris.contacts.weblayer;


import com.tkouleris.contacts.service.ContactService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


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
    }

}
