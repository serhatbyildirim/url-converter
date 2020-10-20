package com.trendyol.sample.controller;

import com.trendyol.sample.service.UrlConvertBuilderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UrlController.class)
public class UrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlConvertBuilderService urlConvertBuilderService;

    @Test
    public void it_should_convert() throws Exception {
        // given
        // when
        mockMvc.perform(get("/urls")
                .contentType(MediaType.APPLICATION_JSON)
                .param("url", "trendyol.com"))
                .andExpect(status().isOk());

        //Then
        verify(urlConvertBuilderService).build("trendyol.com");
    }
}