package me.whiteship.testspringbootstarter.sample;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SampleController6.class)
public class SampleControllerTest6 {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SampleService mockSampleService;

    @Test
    public void test() throws Exception {
        when(mockSampleService.getName()).thenReturn("whiteship");

        mockMvc.perform(get("/hello6"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello whiteship"));

        assertThat(outputCapture.toString())
                .contains("holoman")
                .contains("skip");
    }

}