package me.whiteship.profilespringbootstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProfileSpringBootStarterApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ProfileSpringBootStarterApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

}
