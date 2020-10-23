package com.trendyol.sample.controller.advice;

import com.trendyol.sample.exception.UrlConvertException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TestController.class)
public class GlobalControllerExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void it_should_respond_with_http_500_when_genericException_occurred() throws Exception {
        //given

        //when
        ResultActions resultActions = mockMvc.perform(get("/generic-exception"));

        //then
        resultActions
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.exception", is("GenericException")))
                .andExpect(jsonPath("$.errors[0]", is("Generic exception occurred.")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }

    @Test
    public void it_should_respond_with_http_400_when_urlConvertException_occurred() throws Exception {
        //given

        //when
        ResultActions resultActions = mockMvc.perform(get("/url-convert-exception"));

        //then
        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.exception", is("UrlConvertException")))
                .andExpect(jsonPath("$.errors[0]", is("Invalid Url")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }
}

@RestController
class TestController {

    @GetMapping("/generic-exception")
    public void throwException() throws Exception {
        throw new Exception("generic exception occurred");
    }

    @GetMapping("/url-convert-exception")
    public void throwUrlConvertException() {
        throw new UrlConvertException();
    }
}