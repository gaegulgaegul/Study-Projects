package me.whiteship.neo4jspringbootstarter;

import me.whiteship.neo4jspringbootstarter.account.Account;
import me.whiteship.neo4jspringbootstarter.account.AccountRepository;
import me.whiteship.neo4jspringbootstarter.account.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Neo4jRunner implements ApplicationRunner {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setUsername("whiteship");
        account.setEmail("whiteship@email.com");

        Role role = new Role();
        role.setName("admin");

        account.getRoles().add(role);

        accountRepository.save(account);

        System.out.println("finished");
    }
}
