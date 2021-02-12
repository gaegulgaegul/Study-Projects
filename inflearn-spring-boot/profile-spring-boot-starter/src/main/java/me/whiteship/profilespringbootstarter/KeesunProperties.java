package me.whiteship.profilespringbootstarter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@ConfigurationProperties("keesun")
@Getter
@Setter
public class KeesunProperties {

    private String name;

    private int age;

    private String fullName;

    private Duration sessionTimeout;

}
