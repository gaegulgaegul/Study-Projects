package me.whiteship.propertiesspringbootstarter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/config.properties")
@ConfigurationProperties("local")
@Profile("!dev")
@Setter
@Getter
public class ConfigLocalProperties {

    private String path;

}
