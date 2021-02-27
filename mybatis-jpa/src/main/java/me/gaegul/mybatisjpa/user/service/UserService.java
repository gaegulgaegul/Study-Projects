package me.gaegul.mybatisjpa.user.service;

import lombok.RequiredArgsConstructor;
import me.gaegul.mybatisjpa.user.mapper.UserMapper;
import me.gaegul.mybatisjpa.user.model.User;
import me.gaegul.mybatisjpa.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<User> getUsers() {
        return userMapper.selectUsers();
    }

    public List<User> saveUsers(List<User> users) {
        userRepository.saveAll(users);
        userRepository.flush();

        List<User> users1 = userMapper.selectUsers();
        return users1;
    }

}
