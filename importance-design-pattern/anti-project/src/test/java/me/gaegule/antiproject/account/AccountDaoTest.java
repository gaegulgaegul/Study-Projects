package me.gaegule.antiproject.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AccountDaoTest {

    @Autowired
    DataSource dataSource;

    @Test
    void selectAllTest() {
        AccountDao accountDao = new AccountDao(dataSource);
        List<Account> accounts = accountDao.selectAll();
        assertEquals(accounts.size(), 1);
        assertEquals(accounts.get(0).getId(), 1L);
        assertEquals(accounts.get(0).getUsername(), "gaegul");
        assertEquals(accounts.get(0).getAge(), 20);
    }

    @Test
    void selectAccountByUsernameAndAgeGreaterThanTest() {
        AccountDao accountDao = new AccountDao(dataSource);
        List<String> params = Arrays.asList("gaegul", "10");
        Account account = accountDao.selectAccountByUsernameAndAgeGreaterThan(params);
        assertEquals(account.getId(), 1);
        assertEquals(account.getUsername(), "gaegul");
        assertEquals(account.getAge(), 20);
    }

}