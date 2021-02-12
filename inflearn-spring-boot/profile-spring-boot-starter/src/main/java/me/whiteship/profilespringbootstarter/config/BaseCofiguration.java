package me.whiteship.profilespringbootstarter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Configuration
public class BaseCofiguration {

    @Bean
    public String hello() {
        return "hello";
    }

}
