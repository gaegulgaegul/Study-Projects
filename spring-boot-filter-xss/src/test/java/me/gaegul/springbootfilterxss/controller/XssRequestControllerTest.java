package me.gaegul.springbootfilterxss.controller;

import me.gaegul.springbootfilterxss.dto.XssRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class XssRequestControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void 태그_치환() {
        String content = "<li>content</li>";
        String expected = "&lt;li&gt;content&lt;/li&gt;";

        ResponseEntity<XssRequestDto> response = restTemplate.postForEntity("/xss", new XssRequestDto(content), XssRequestDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getContent()).isEqualTo(expected);
    }

}