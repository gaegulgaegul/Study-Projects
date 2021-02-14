package me.whiteship.testspringbootstarter.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController5 {

    @Autowired
    private SampleService sampleService;

    @GetMapping("/hello5")
    public String hello() {
        return "hello " + sampleService.getName();
    }

}
