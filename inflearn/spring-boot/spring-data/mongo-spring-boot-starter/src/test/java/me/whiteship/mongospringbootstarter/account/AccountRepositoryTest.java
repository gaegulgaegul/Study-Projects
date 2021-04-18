package me.whiteship.mongospringbootstarter.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataMongoTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void test() {
        Account account = new Account();
        account.setUsername("keesun");
        account.setEmail("whiteship@mail.com");

        accountRepository.save(account);

        Optional<Account> byId = accountRepository.findById(account.getId());
        assertThat(byId).isNotEmpty();

        Optional<Account> byEmail = accountRepository.findByEmail(account.getEmail());
        assertThat(byEmail).isNotEmpty();
        assertThat(byEmail.get().getUsername()).isEqualTo("keesun");
    }

}