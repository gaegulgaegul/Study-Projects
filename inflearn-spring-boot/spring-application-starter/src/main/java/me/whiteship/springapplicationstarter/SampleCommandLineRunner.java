package me.whiteship.springapplicationstarter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SampleCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("======================");
        System.out.println("CommandLineRunner");
        Arrays.stream(args).forEach(System.out::println);
        System.out.println("======================");
    }
}
