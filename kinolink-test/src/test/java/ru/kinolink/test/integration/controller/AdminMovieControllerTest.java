package ru.kinolink.test.integration.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.kinolink.test.integration.TestAppConfig;
import ru.kinolink.web.webapp.Url;
import ru.kinolink.web.webapp.controller.AdminController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@ContextConfiguration( classes = {TestAppConfig.class})
@WebAppConfiguration
public class AdminMovieControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testAdminPageMovies() throws Exception {
        this.mockMvc.perform(get(Url.ADMIN_PAGE_MOVIES))
                .andExpect(status().isOk())
                .andExpect(view().name(AdminController.VIEW_ADMIN_PAGE_MOVIES));
    }
}