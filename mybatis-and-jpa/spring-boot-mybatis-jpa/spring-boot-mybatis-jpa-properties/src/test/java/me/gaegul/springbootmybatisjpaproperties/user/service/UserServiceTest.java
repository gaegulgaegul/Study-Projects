package me.gaegul.springbootmybatisjpaproperties.user.service;

import me.gaegul.springbootmybatisjpaproperties.user.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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

        List<User> findUsers = userService.getUsers();
        assertEquals(findUsers.size(), 3);
    }

}