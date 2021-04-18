package me.whiteship.testspringbootstarter.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleControllerTest3 {

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    SampleService mockSampleService;

    @Test
    public void test() {
        when(mockSampleService.getName()).thenReturn("whiteship");

        String result = testRestTemplate.getForObject("/hello3", String.class);
        assertThat(result).isEqualTo("hello whiteship");
    }

}
