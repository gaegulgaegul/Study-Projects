package me.whiteship.propertiesspringbootstarter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(locations = "classpath:/test.properties")
@SpringBootTest
class PropertiesSpringBootStarterApplicationTests {

    @Autowired
    Environment environment;

    @Test
    void contextLoads() {
        Assertions.assertEquals(environment.getProperty("keesun.name"), "keesun2");
    }

}
