package me.whiteship.testspringbootstarter.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SampleController5.class)
public class SampleControllerTest5 {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SampleService mockSampleService;

    @Test
    public void Test() throws Exception {
        when(mockSampleService.getName()).thenReturn("whiteship");

        mockMvc.perform(get("/hello5"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello whiteship"))
                .andDo(print());
    }

}