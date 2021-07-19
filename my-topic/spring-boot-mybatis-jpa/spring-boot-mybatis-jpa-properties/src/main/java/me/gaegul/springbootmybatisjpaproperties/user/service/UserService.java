package me.gaegul.springbootmybatisjpaproperties.user.service;

import lombok.RequiredArgsConstructor;
import me.gaegul.springbootmybatisjpaproperties.user.mapper.UserMapper;
import me.gaegul.springbootmybatisjpaproperties.user.model.User;
import me.gaegul.springbootmybatisjpaproperties.user.repository.UserRepository;
import org.springframework.stereotype.Service;

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

        return userMapper.selectUsers();
    }
}
