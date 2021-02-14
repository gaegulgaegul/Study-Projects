package me.whiteship.testspringbootstarter.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleControllerTest4 {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    SampleService mockSampleService;

    @Test
    public void test() {
        when(mockSampleService.getName()).thenReturn("whiteship");

        webTestClient.get().uri("/hello4").exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("hello whiteship");
    }
}
