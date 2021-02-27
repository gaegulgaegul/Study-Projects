package me.gaegul.mybatisjpa.user.service;

import me.gaegul.mybatisjpa.user.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void test() {
        List<User> users = new ArrayList<>();
        users.add(User.builder().id("1").name("test1").build());
        users.add(User.builder().id("2").name("test2").build());
        users.add(User.builder().id("3").name("test3").build());

        List<User> savedUsers = userService.saveUsers(users);

        assertEquals(savedUsers.size(), 3);
        assertEquals(savedUsers.get(0).getId(), "1");
        assertEquals(savedUsers.get(1).getId(), "2");
        assertEquals(savedUsers.get(2).getId(), "3");
        assertEquals(savedUsers.get(0).getName(), "test1");
        assertEquals(savedUsers.get(1).getName(), "test2");
        assertEquals(savedUsers.get(2).getName(), "test3");
    }

}