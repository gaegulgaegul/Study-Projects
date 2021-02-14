package me.whiteship.testspringbootstarter.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController6 {

    Logger logger = LoggerFactory.getLogger(SampleController6.class);

    @Autowired
    private SampleService sampleService;

    @GetMapping("/hello6")
    public String hello() {
        logger.info("holoman");
        System.out.println("skip");
        return "hello " + sampleService.getName();
    }

}
