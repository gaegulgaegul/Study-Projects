package me.gaegul.mybatisjpa.user.service;

import me.gaegul.mybatisjpa.user.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailServiceTest {

    @Autowired
    UserDetailService userDetailService;

    @Test
    public void AOP_트랜잭션_테스트() {
        List<User> users = new ArrayList<>();
        users.add(User.builder().id("10").name("test10").build());
        users.add(User.builder().id("20").name("test20").build());
        users.add(User.builder().id("30").name("test30").build());

        List<User> savedUsers = userDetailService.saveUsers(users);

        assertEquals(savedUsers.size(), 3);
        assertEquals(savedUsers.get(0).getId(), "10");
        assertEquals(savedUsers.get(1).getId(), "20");
        assertEquals(savedUsers.get(2).getId(), "30");
        assertEquals(savedUsers.get(0).getName(), "test10");
        assertEquals(savedUsers.get(1).getName(), "test20");
        assertEquals(savedUsers.get(2).getName(), "test30");
    }

    @Test
    public void AOP_트랜잭션_오류_발생하는_로직() {
        assertThatThrownBy(() -> {
            userDetailService.saveUsersWithAop();
        });
    }

}