package com.tkouleris.contacts.weblayer;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.runner.RunWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
//@RunWith(SpringRunner.class)
public class ContactsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contactsListLoadsTest() throws Exception {
        mockMvc.perform(get("/api/contacts/all"))
                .andExpect(status().isOk());
    }

}
