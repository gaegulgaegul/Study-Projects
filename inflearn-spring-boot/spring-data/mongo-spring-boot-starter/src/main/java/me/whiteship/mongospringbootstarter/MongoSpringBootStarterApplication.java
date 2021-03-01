package me.whiteship.mongospringbootstarter;

import me.whiteship.mongospringbootstarter.account.Account;
import me.whiteship.mongospringbootstarter.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class MongoSpringBootStarterApplication {

    @Autowired
    AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(MongoSpringBootStarterApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            Account account = new Account();
            account.setEmail("whiteship@email.com");
            account.setUsername("whiteship");
            accountRepository.insert(account);

            System.out.println("finished");
        };
    }

}
