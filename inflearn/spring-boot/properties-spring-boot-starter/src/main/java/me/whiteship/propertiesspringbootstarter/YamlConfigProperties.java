package me.whiteship.propertiesspringbootstarter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("yaml")
@Setter
@Getter
public class YamlConfigProperties {

    private String path;

}
