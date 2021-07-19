package me.gaegul.mybatisjpa.user.service;

import lombok.RequiredArgsConstructor;
import me.gaegul.mybatisjpa.user.mapper.UserMapper;
import me.gaegul.mybatisjpa.user.model.User;
import me.gaegul.mybatisjpa.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<User> getUsers() {
        return userMapper.selectUsers();
    }

    public List<User> saveUsers(List<User> users) {
        userRepository.saveAll(users);
        userRepository.flush();
        return userMapper.selectUsers();
    }

    public List<User> saveUsersWithAop() {
        List<User> test = userMapper.selectUsers();
        System.out.println("before insert count : " + test.size());

        List<User> users = new ArrayList<>();
        users.add(User.builder().id("1").name("test1").build());
        users.add(User.builder().id("2").name("test2").build());
        users.add(User.builder().id("3").name("test3").build());

        userRepository.saveAll(users);
        userRepository.flush();

        if (true) {
            throw new IllegalArgumentException("test");
        }

        return userMapper.selectUsers();
    }
}
