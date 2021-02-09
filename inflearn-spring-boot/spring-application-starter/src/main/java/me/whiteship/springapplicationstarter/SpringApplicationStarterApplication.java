package me.whiteship.springapplicationstarter;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

@SpringBootApplication
public class SpringApplicationStarterApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(SpringApplicationStarterApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

}
