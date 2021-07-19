package me.gaegul.runner;

import lombok.extern.slf4j.Slf4j;
import me.gaegul.annotation.TestContextContainer;
import me.gaegul.crypto.CryptoUtil;
import me.gaegul.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppRunner implements ApplicationRunner {

    @Autowired
    TestContextContainer testContextContainer;

    @Autowired
    CryptoUtil cryptoUtil;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = testContextContainer.get(Account.class);
        account.setPassword("12341234");
        account.setEmail("email@email.com");
        account.setPhoneNumber("010-1234-1234");
        account.setCi("21948-u5bhwf-b912-asf92");
        cryptoUtil.encrypt(account);
        log.info(account.toString());
    }

}
