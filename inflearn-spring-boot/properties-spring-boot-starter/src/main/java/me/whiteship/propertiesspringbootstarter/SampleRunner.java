package me.whiteship.propertiesspringbootstarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

    @Autowired
    private KeesunProperties keesunProperties;

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private YamlConfigProperties yamlConfigProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("===================");
        System.out.println(keesunProperties.getName());
        System.out.println(keesunProperties.getAge());
        System.out.println(keesunProperties.getSessionTimeout());
        System.out.println("===================");
        System.out.println(configProperties.getPath());
        System.out.println("===================");
        System.out.println(yamlConfigProperties.getPath());
        System.out.println("===================");
    }
}
