package me.gaegul.springbootfilterxss;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class XssController {

    @PostMapping("/xss")
    public String xss(@RequestBody XssRequestDto xssRequestDto) {
        log.info("requestDto={}", xssRequestDto);
        return xssRequestDto.getContent();
    }

}
