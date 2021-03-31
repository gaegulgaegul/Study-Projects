package me.gaegul.methodtemplatepattern.account;

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
        List<Account> all = accountDao.findAll();
        assertEquals(all.size(), 1);
    }

    @Test
    void findByUsernameTest() {
        AccountDao accountDao = new AccountDao(dataSource);
        Account account = accountDao.findByUsername("gaegul");
        assertEquals(account.getId(), 1L);
        assertEquals(account.getUsername(), "gaegul");
        assertEquals(account.getAge(), 20);
    }

}