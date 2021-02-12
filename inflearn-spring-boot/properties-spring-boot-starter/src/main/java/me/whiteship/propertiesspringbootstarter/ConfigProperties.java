package me.whiteship.propertiesspringbootstarter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"classpath:/config-${spring.profiles.active}.properties"})
@ConfigurationProperties("config")
@Setter
@Getter
public class ConfigProperties {

    private String path;

}
