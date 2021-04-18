package me.whiteship.adminspringbootstarter;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class AdminSpringBootStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminSpringBootStarterApplication.class, args);
    }

}
