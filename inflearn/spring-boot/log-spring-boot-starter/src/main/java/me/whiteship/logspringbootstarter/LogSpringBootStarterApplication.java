package me.whiteship.logspringbootstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogSpringBootStarterApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(LogSpringBootStarterApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

}
