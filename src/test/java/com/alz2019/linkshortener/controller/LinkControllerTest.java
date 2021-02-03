package com.alz2019.linkshortener.controller;

import com.alz2019.linkshortener.model.Link;
import com.alz2019.linkshortener.service.LinkService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LinkController.class)
public class LinkControllerTest {
    private static final String SHORT_LINK = "googru";
    private static final String LONG_LINK = "https://www.google.ru";
    private static final Link mock = new Link(SHORT_LINK, LONG_LINK);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LinkService linkService;

    @Test
    public void testCreateShortLink() throws Exception {
        when(linkService.shorten(anyString())).thenReturn(mock);
        ResultActions resultActions = mockMvc.perform(get("/short").param("long_link", LONG_LINK));
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("short_link").value(SHORT_LINK))
                .andExpect(jsonPath("long_link").value(LONG_LINK));
    }

    @Test
    public void testEnlarge() throws Exception {
        when(linkService.enlarge(SHORT_LINK)).thenReturn(mock);
        String response = "{\"short_link\":\"googru\",\"long_link\":\"https://www.google.ru\"}";
        mockMvc.perform(get("/").param("short_link", SHORT_LINK))
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }
}