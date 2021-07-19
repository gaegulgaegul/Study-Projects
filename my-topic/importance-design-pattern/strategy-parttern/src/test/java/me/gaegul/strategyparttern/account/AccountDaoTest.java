package me.gaegul.strategyparttern.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountDaoTest {

    @Autowired
    DataSource dataSource;

    @Test
    void findAllTest() {
        AccountDao accountDao = new AccountDao(dataSource);
        List<Account> accounts = accountDao.findAll();
        assertEquals(accounts.size(), 1);
    }

    @Test
    void findByUsername() {
        AccountDao accountDao = new AccountDao(dataSource);
        Account account = accountDao.findByUsername("gaegul");
        assertEquals(account.getId(), 1L);
        assertEquals(account.getUsername(), "gaegul");
        assertEquals(account.getAge(), 20);
    }

}