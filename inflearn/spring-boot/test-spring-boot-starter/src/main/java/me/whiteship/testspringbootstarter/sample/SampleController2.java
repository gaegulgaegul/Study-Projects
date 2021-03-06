package me.whiteship.testspringbootstarter.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController2 {

    @Autowired
    private SampleService sampleService;

    @GetMapping("/hello2")
    public String hello() {
        return "hello " + sampleService.getName();
    }

}
